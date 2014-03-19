var answerBall = printBall();
var count = { cnt : 1, clickCnt : 0, result : "" };
var buttons = { btn1 : null, btn2 : null, btn3 : null,
                btn4 : null, btn5 : null, btn6 : null, 
                btn7 : null, btn8 : null, btn9 : null, 
                btns : null, btnc : null, btnre : null, 
                btncl : null, btnx : null };
var timer = {startTime : null, timerID : null, running : false};

window.onload = function() {
	buttons.btns = document.getElementById("start");
	buttons.btns.addEventListener("click", start);
	
	buttons.btn1 = document.getElementById("input_btn1");
	buttons.btn1.addEventListener("click", inputNumber);
	buttons.btn1.setAttribute("disabled", "disabled");
	buttons.btn2 = document.getElementById("input_btn2");
	buttons.btn2.addEventListener("click", inputNumber);
	buttons.btn2.setAttribute("disabled", "disabled");
	buttons.btn3 = document.getElementById("input_btn3");
	buttons.btn3.addEventListener("click", inputNumber);
	buttons.btn3.setAttribute("disabled", "disabled");
	buttons.btn4 = document.getElementById("input_btn4");
	buttons.btn4.addEventListener("click", inputNumber);
	buttons.btn4.setAttribute("disabled", "disabled");
	buttons.btn5 = document.getElementById("input_btn5");
	buttons.btn5.addEventListener("click", inputNumber);
	buttons.btn5.setAttribute("disabled", "disabled");
	buttons.btn6 = document.getElementById("input_btn6");
	buttons.btn6.addEventListener("click", inputNumber);
	buttons.btn6.setAttribute("disabled", "disabled");
	buttons.btn7 = document.getElementById("input_btn7");
	buttons.btn7.addEventListener("click", inputNumber);
	buttons.btn7.setAttribute("disabled", "disabled");
	buttons.btn8 = document.getElementById("input_btn8");
	buttons.btn8.addEventListener("click", inputNumber);
	buttons.btn8.setAttribute("disabled", "disabled");
	buttons.btn9 = document.getElementById("input_btn9");
	buttons.btn9.addEventListener("click", inputNumber);
	buttons.btn9.setAttribute("disabled", "disabled");
	buttons.btnc = document.getElementById("cheat");
	buttons.btnc.addEventListener("click", cheat);
	buttons.btnc.setAttribute("disabled", "disabled");
	buttons.btnre = document.getElementById("restart");
	buttons.btnre.addEventListener("click", restart);
	buttons.btnre.setAttribute("disabled", "disabled");
	buttons.btncl = document.getElementById("clear");
	buttons.btncl.addEventListener("click", clear);
	buttons.btncl.setAttribute("disabled", "disabled");
	buttons.btnx = document.getElementById("stop");
	buttons.btnx.addEventListener("click", stop);
	buttons.btnx.setAttribute("disabled", "disabled");
	
	document.getElementById("clock").setAttribute("disabled", "disabled");	
	document.onkeydown = checkStartKeycode;
};

function start() {
	alert("게임 시작!");
	document.getElementById("inning_num").value = count.cnt;
	var start = new Date();
	timer.startTime = start.getTime();
    if(timer.running) return
    showTime()
    timer.running = true
    
	buttons.btn1.removeAttribute("disabled");
	buttons.btn2.removeAttribute("disabled");
	buttons.btn3.removeAttribute("disabled");
	buttons.btn4.removeAttribute("disabled");
	buttons.btn5.removeAttribute("disabled");
	buttons.btn6.removeAttribute("disabled");
	buttons.btn7.removeAttribute("disabled");
	buttons.btn8.removeAttribute("disabled");
	buttons.btn9.removeAttribute("disabled");
	buttons.btnc.removeAttribute("disabled");
	buttons.btnre.removeAttribute("disabled");
	buttons.btncl.removeAttribute("disabled");
	buttons.btnx.removeAttribute("disabled");
	buttons.btns.setAttribute("disabled", "disabled");
	
	document.onkeydown = checkKeycode;
}

function stop() {
	if(timer.running) {
		clearTimeout(timer.timerID)
		timer.running = false
	}
	
	buttons.btn1.setAttribute("disabled", "disabled");
	buttons.btn2.setAttribute("disabled", "disabled");
	buttons.btn3.setAttribute("disabled", "disabled");
	buttons.btn4.setAttribute("disabled", "disabled");
	buttons.btn5.setAttribute("disabled", "disabled");
	buttons.btn6.setAttribute("disabled", "disabled");
	buttons.btn7.setAttribute("disabled", "disabled");
	buttons.btn8.setAttribute("disabled", "disabled");
	buttons.btn9.setAttribute("disabled", "disabled");
	buttons.btnc.setAttribute("disabled", "disabled");
	buttons.btnre.setAttribute("disabled", "disabled");
	buttons.btncl.setAttribute("disabled", "disabled");
	buttons.btnx.setAttribute("disabled", "disabled");
	buttons.btns.removeAttribute("disabled");
	
	document.onkeydown = checkStartKeycode;
	
	document.getElementById("input3").value = "";
	document.getElementById("input2").value = "";
	document.getElementById("input1").value = "";
	
	count.cnt = 1;
	document.getElementById("inning_num").value = "";
	document.getElementById("result").value = "";
	
	count.clickCnt = 0;
	document.getElementById("clock").value = "";	// 이거 확인 좀 밑에 리스타트에서 실행해야할지 여기서 해야할지..
}

function checkStartKeycode(e) {
	var keycode;  
	if (window.event){ 
		keycode = window.event.keyCode;
	}		
	if (e) {
		keycode = e.which;
		if(keycode==83){start();}	// s press( Game Start )
	}
}

function checkKeycode(e) {
	var keycode;  
	if (window.event){ 
		keycode = window.event.keyCode;
	}		
	if (e) {
		keycode = e.which;
		if(keycode==49){inputNumber(1);}
		if(keycode==50){inputNumber(2);}
		if(keycode==51){inputNumber(3);}
		if(keycode==52){inputNumber(4);}
		if(keycode==53){inputNumber(5);}
		if(keycode==54){inputNumber(6);}
		if(keycode==55){inputNumber(7);}
		if(keycode==56){inputNumber(8);}
		if(keycode==57){inputNumber(9);}
		if(keycode==67){clear();}	// c press( Clear input Space ) 
		if(keycode==82){restart();}	// r press( Game Restart )
		if(keycode==88){cheat();}	// x press( View Answer )
		if(keycode==81){stop();}	// q press( Game Stop )
	}
}

function showTime() {
	var now = new Date();
	var currentTime = now.getTime();
	var timeValue = (currentTime - timer.startTime)/1000;
	document.getElementById("clock").value = timeValue;
	timer.timerID = setTimeout(function(){
		showTime();
	},10);
}

function makeBall(){
		
	var answerBall = new Array();
	for(var i = 0; i < 3; i++){
		var randomNum = Math.random() * 10;
		var str = String(randomNum);
		var num = +(str.substring(0,1));
		if(num == answerBall[i-1]||num == answerBall[i-2]||num == 0){
			continue;
		}
		answerBall[i] = num;
//		answerBall.sort(function(a,b) {return a-b}); //세개의 숫자를 순서대로 정렬
	}
	return answerBall;
}

function printBall(){
	while(true){
		answerBall = makeBall();
		if (answerBall.length != 3 || answerBall[0] == undefined || answerBall[1] == undefined || answerBall[2] == undefined) {
			continue;
		}
		break;
	}

return answerBall;
}

function checkBall(num1, num2, num3) {
	var throwBall = new Array(3);
	throwBall = [num1, num2, num3];
	var ball = 0;
	var strike = 0;
	for(var i = 0; i < 3; i++){
		for(var j = 0; j < 3; j++){
			if(answerBall[i]==throwBall[j]){
				ball++;
			}
		}
	}
	for(var k = 0; k <3; k++){
		if(answerBall[k]==throwBall[k]){
			strike++;
		}
	}
	if (strike == 3){
		count.result = "end";
		alert("축하합니다.");
		stop();
	} else {
		ball = ball - strike;
		count.result = ball + "ball / " + strike + "strike";
		count.cnt++;
	}
	return count.result;
}

function printResult() {
	var num1 = document.getElementById("input3").value;
	var num2 = document.getElementById("input2").value;
	var num3 = document.getElementById("input1").value;
		var score = checkBall(num1, num2, num3);
		if(score=="end"){
			score="";
			document.getElementById("result").value = score;
		} else {
			score = "\n\n [현재 스코어]\n\n  " + score +
			"\n\n\n\n\n\n [입력한 숫자]\n\n  " + 
			num1 + " / " + num2 + " / " +  num3;
			document.getElementById("result").value = score; 
			document.getElementById("input3").value = "";
			document.getElementById("input2").value = "";
			document.getElementById("input1").value = "";
			document.getElementById("inning_num").value = count.cnt;
			if(count.cnt==10&&count.result!="end"){
				alert("실패!");
				stop();
			}
		}
}

function inputNumber(a) {
	var num;
	num = window.event.srcElement.value;
	
	if(!isNaN(a)) {
		num = a;
	}
	if(count.clickCnt == 0){
		document.getElementById("input3").value = "";
		document.getElementById("input2").value = "";
		document.getElementById("input1").value = "";
	}
	document.getElementById("input" + (3 - count.clickCnt)).value = num;
	var num1 = document.getElementById("input3").value;
	var num2 = document.getElementById("input2").value;
	var num3 = document.getElementById("input1").value;
	if(num2!=""&&(num1==num2||num2==num3||num3==num1)) {
		alert("세개의 숫자는 전부 달라야합니다.");
		document.getElementById("input" + (3 - count.clickCnt)).value = "";
	} else {
		count.clickCnt++
		if (count.clickCnt == 3){
			count.clickCnt = 0;
			printResult();
		}
	}
}

function cheat() {
	document.getElementById("result").value = answerBall;
}

function restart() {
	stop();
	printBall();
	count.clickCnt = 0;
	document.getElementById("clock").value = "";
	
	start();
}

function clear() {
	document.getElementById("input3").value = "";
	document.getElementById("input2").value = "";
	document.getElementById("input1").value = "";
	
	count.clickCnt = 0;
}