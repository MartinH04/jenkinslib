package org.devops

//构建类型
def Build(buildType,buildShell){
    //定义一个工具字典
    def buildTools = ["mvn":"M2","ant":"ANT","gradle":"GRADLE","npm":"NPM"]
    
    println("The Build Type Current Seleted is ${buildType}")
    
    //调用jenkins全局配置工具
    buildHome = tool buildTools[buildType]
    
    //npm需要重新导出全局变量，否则不太生效
    if("${buildType}" == "npm"){
        sh """
          export NODE_HOME=${buildHome}
          export PATH=\$NODE_HOME/bin:\$PATH
          ${buildHome}/bin/${buildType} ${buildShell}"""
    } else {
        sh "${buildHome}/bin/${buildType} ${buildShell}"
    }
}
