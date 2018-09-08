let formula = "属性(['存款'])+模型分数('收入')"
//formula='a'
const getResp = (formula) => {
    const res = formula.match(/模型分数\('(.+?)'\)/) 
    return !!res && res.length>1?res[1]:'not found'
}

console.log(getResp(formula))


const getAttr = (formula) => {
    const res = formula.match(/属性\(\['(.+?)'\]\)/) 
    return !!res && res.length>1?res[1]:'not found'
}

console.log(getAttr(formula))