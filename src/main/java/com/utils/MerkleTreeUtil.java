package com.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class MerkleTreeUtil {

    public static class ProofNode {
        private String position;
        private String hash;

        public ProofNode() {
        }

        public ProofNode(String position, String hash) {
            this.position = position;
            this.hash = hash;
        }

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public String getHash() {
            return hash;
        }

        public void setHash(String hash) {
            this.hash = hash;
        }
    }

    public static String sha256(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-256 algorithm not available", e);
        }
    }

    public static String buildRoot(List<String> leaves) {
        if (leaves == null || leaves.isEmpty()) {
            return "";
        }
        List<String> current = new ArrayList<>(leaves);
        while (current.size() > 1) {
            List<String> nextLevel = new ArrayList<>();
            for (int i = 0; i < current.size(); i += 2) {
                String left = current.get(i);
                String right = (i + 1 < current.size()) ? current.get(i + 1) : left;
                nextLevel.add(sha256(left + right));
            }
            current = nextLevel;
        }
        return current.get(0);
    }

    public static List<ProofNode> buildProof(List<String> leaves, int targetIndex) {
        List<ProofNode> proof = new ArrayList<>();
        if (leaves == null || leaves.isEmpty() || targetIndex < 0 || targetIndex >= leaves.size()) {
            return proof;
        }

        List<String> currentLevel = new ArrayList<>(leaves);
        int index = targetIndex;

        while (currentLevel.size() > 1) {
            List<String> nextLevel = new ArrayList<>();

            for (int i = 0; i < currentLevel.size(); i += 2) {
                String left = currentLevel.get(i);
                String right = (i + 1 < currentLevel.size()) ? currentLevel.get(i + 1) : left;

                if (i == index || i + 1 == index) {
                    if (index % 2 == 0) {
                        proof.add(new ProofNode("RIGHT", right));
                    } else {
                        proof.add(new ProofNode("LEFT", left));
                    }
                    index = nextLevel.size();
                }

                nextLevel.add(sha256(left + right));
            }
            currentLevel = nextLevel;
        }

        return proof;
    }

    public static boolean verifyProof(String leafHash, List<ProofNode> proof, String expectedRoot) {
        if (leafHash == null || leafHash.trim().isEmpty() || expectedRoot == null || expectedRoot.trim().isEmpty()) {
            return false;
        }

        String computedHash = leafHash;
        if (proof != null) {
            for (ProofNode node : proof) {
                if (node == null || node.getHash() == null || node.getPosition() == null) {
                    return false;
                }
                if ("LEFT".equalsIgnoreCase(node.getPosition())) {
                    computedHash = sha256(node.getHash() + computedHash);
                } else if ("RIGHT".equalsIgnoreCase(node.getPosition())) {
                    computedHash = sha256(computedHash + node.getHash());
                } else {
                    return false;
                }
            }
        }

        return computedHash.equalsIgnoreCase(expectedRoot);
    }
}
