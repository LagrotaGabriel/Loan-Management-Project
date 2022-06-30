/* =========================== RESPONSIVIDADE DA TELA =========================== */

window.onload = responsive();
window.onresize = doALoadOfStuff;

function responsive(){

	bodyWidth = document.getElementById('body').clientWidth;
	bodyHeight = document.getElementById('body').clientHeight;

	headerButton = document.getElementsByClassName('button_header');

	if(bodyWidth > 1200){

		console.log("Muito grande");
		for(var i = 0; i < headerButton.length; i++){
			headerButton[i].style.fontSize="1.5rem";
			headerButton[i].style.marginRight="30px";
		}
		document.getElementById('first_button_header').style.marginRight="20px";

	}
	else if(bodyWidth <= 1200 && bodyWidth > 992){

		console.log("Grande");
		for(var i = 0; i < headerButton.length; i++){
			headerButton[i].style.fontSize="1.5rem";
			headerButton[i].style.marginRight="30px";
		}
		document.getElementById('first_button_header').style.marginRight="20px";

	}
	else if(bodyWidth <= 992 && bodyWidth > 768){

		console.log('Médio');
		for(var i = 0; i < headerButton.length; i++){
			headerButton[i].style.fontSize="1.05rem";
			headerButton[i].style.marginRight="30px";
		}
		document.getElementById('first_button_header').style.marginRight="20px";

	
	}
	else if(bodyWidth <= 768 && bodyWidth > 540){

		console.log('Pequeno');
		for(var i = 0; i < headerButton.length; i++){
			headerButton[i].style.fontSize="1.05rem";
			headerButton[i].style.marginRight="10px";
		}
		document.getElementById('first_button_header').style.marginRight="10px";

	}
	else if(bodyWidth < 540){

		console.log('Muito pequeno');
		for(var i = 0; i < headerButton.length; i++){
			headerButton[i].style.fontSize="1.05rem";
			headerButton[i].style.marginRight="10px";
		}
		document.getElementById('first_button_header').style.marginRight="10px";

	}

}

function doALoadOfStuff() {
	document.getElementById('global').style.transition="2s";
	responsive();
}