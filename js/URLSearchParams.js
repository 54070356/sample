const params={a:1,b:null,c:[3,4]}
var searchParams = new URLSearchParams();
console.log(searchParams.toString()) //empty
for(let name in params) {
  if(params[name]) searchParams.append(name,params[name])
}
console.log("final="+searchParams.toString())//a=1&c=3%2C4
