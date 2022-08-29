"use strict";
let valueSave;

function clearresults(){
  document.getElementById("result").innerHTML="0";
}

function removezero(){
	let value = document.getElementById("result").innerHTML;
	if (value=="0"){
		value=" "
		document.getElementById("result").innerHTML = value;
	}
}

function deleteDuplicates(){
	let value = document.getElementById("result").innerHTML;
	if (value.includes("++")){
		value=valueSave
		document.getElementById("result").innerHTML = value;
	} 
	if (value.includes("--")){
		value=valueSave
		document.getElementById("result").innerHTML = value;
	} 
}

function backupSave(){
	valueSave = document.getElementById("result").innerHTML
}

function insertvalue(myvalue){
	removezero()
	backupSave()
	document.getElementById("result").innerHTML += myvalue;	
	deleteDuplicates()
}

/*
function hidding() {
	var x = document.getElementById("input");
	if (x.style.display === "none") {
	  x.style.display = "block";
	} else {
	  x.style.display = "none";
	}
  }
*/
function percent() {
	let value = document.getElementById("result").innerHTML / 100;
	document.getElementById("result").innerHTML = value;
}

function calculate() {
     removezero()
     let temp = document.getElementById("result").innerHTML;
     let solved = eval(temp);
     document.getElementById("result").innerHTML = solved;
}

