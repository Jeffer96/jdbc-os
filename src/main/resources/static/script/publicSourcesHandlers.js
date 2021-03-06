function loadReady(){
	showLoading();
	fetch("/login/getUrl",
		{headers : new Headers({'Authorization' : localStorage.getItem(ap) ? tp + localStorage.getItem(ap) : ""})})
		.then(function(response){return response.text();})
		.then(function(data){loadWith(data);})
		.catch(function(error){loadWith("/login");});
}
function loadWith(goToUrl){
	showLoading();
	$.ajax({url: goToUrl,
		type: "GET",headers:{ 'Authorization' : localStorage.getItem(ap) ? tp + localStorage.getItem(ap) : ""}})
			.done(function(newUrl){
				current = goToUrl;
				$("#rootContent").html(newUrl);
				setTimeout(function(){
					hideLoading();
				},100);
			}).fail(function(errorRequest) {
				alert(errorRequest.status);
				current = "/login";
				loadWith("/login");
			});
}
function showLoading(){
	document.getElementById("rootContent").style.display="none";
	document.getElementById("loadingLayout").style.display = "block";
}
function hideLoading(){
	document.getElementById("rootContent").style.display="initial";
	document.getElementById("loadingLayout").style.display = "none";
}
const tp = 'Bearer '; 
const ap = "token"; 
var identifier={"/login":function(){sll();},"/administrador":function(){slo();},"/empleado":function(){slo();},"/usuario":function(){slo();}};
var current="";
var visible = false;
var currentDrop = "";
function dropDown(id){
	if (currentDrop == ""){
		currentDrop = id+"DropDown";document.getElementById(currentDrop).style.display = "initial";
	}else if(currentDrop == id+"DropDown"){
		document.getElementById(id+"DropDown").style.display = "none";currentDrop = "";
	}else{
		document.getElementById(currentDrop).style.display = "none";
		currentDrop = id+"DropDown";document.getElementById(currentDrop).style.display = "initial";
	}
}
function toggleVisiblePass(){
	visible = !visible;document.getElementById("toggleVisibility").src = "supportSources/images/" + (visible ? "hide.png" : "show.png");
	document.getElementById("userPass").type = visible ? "text" : "password";
}
function changeInputText(){
	document.getElementById("errorMsg").style.display = "none";
}
function sll(){
	$("#submitButton").click(function(){
		authenticate({"userName":document.getElementById("userNick").value,"userPass":document.getElementById("userPass").value});
	});
	$("#register").click(function(){
		loadWith("/registro");
	});
}
function slo(){
	$("#logOut").click(function(){
		fetch("/logout").catch(function(error){alert("Algo salio mal, se redigira a la pagina de inicio");});
	localStorage.removeItem(ap);
	localStorage.removeItem("currentUser");
	loadWith("/login");});
}
function ready(){
	identifier[current]();
}
function authenticate(oauth){
	showLoading();
	fetch("/login/auth",{method : 'POST',headers : new Headers({'Content-Type': 'application/json'}),body : JSON.stringify(oauth)}).
	then(function(response){
		return response.json();
	}).then(function (data){
		if (data.respCode == "202"){
			document.getElementById("errorMsg").style.display = "none";localStorage.setItem(ap,data.token);
			localStorage.setItem("currentUser",data.userName);loadWith(data.url);
		}else if (data.respCode == "401"){
			hideLoading();document.getElementById("errorMsg").style.display = "initial";
		}else{
			hideLoading();
			alert(data.respCode);
		}}).catch(function (error){
			hideLoading();alert("no se pudo completar la operacion: "+error);
		});
		document.getElementById("userNick").value = "";document.getElementById("userPass").value = "";
}


