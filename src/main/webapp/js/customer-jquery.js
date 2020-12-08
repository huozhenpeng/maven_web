//模拟jquery根据id查找对象
//$就是个方法名称，是合法的
function $(id){
    const obj = document.getElementById(id);
    return obj;
}