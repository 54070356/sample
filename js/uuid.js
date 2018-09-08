function GenNonDuplicateID(randomLength){
 return Number(Math.random().toString().substr(3,randomLength) + Date.now()).toString(36)
}
//GenNonDuplicateID()将生成 rfmipbs8ag0kgkcogc 类似的ID
GenNonDuplicateID()