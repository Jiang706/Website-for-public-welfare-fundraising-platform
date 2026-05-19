package com.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@TableName("merkle_proof")
public class MerkleProofEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Long id;

    private Long juanzengjiluId;

    private String batchNo;

    private Integer leafIndex;

    private String proofJson;

    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat
    private Date createdAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getJuanzengjiluId() {
        return juanzengjiluId;
    }

    public void setJuanzengjiluId(Long juanzengjiluId) {
        this.juanzengjiluId = juanzengjiluId;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public Integer getLeafIndex() {
        return leafIndex;
    }

    public void setLeafIndex(Integer leafIndex) {
        this.leafIndex = leafIndex;
    }

    public String getProofJson() {
        return proofJson;
    }

    public void setProofJson(String proofJson) {
        this.proofJson = proofJson;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
