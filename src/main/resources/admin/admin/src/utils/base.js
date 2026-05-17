const base = {
    get() {
        return {
            url : "http://localhost:8080/springboot51ug89lx/",
            name: "springboot51ug89lx",
            // 退出到首页链接
            indexUrl: 'http://localhost:8080/springboot51ug89lx/front/dist/index.html'
        };
    },
    getProjectName(){
        return {
            projectName: "基于默克尔树技术的透明化慈善募捐管理信息系统"
        } 
    }
}
export default base
