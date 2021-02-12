//Существует много способов выбрать DOM узел; здесь мы получаем форму и электронную почту
//поле ввода, а также элемент span, в который мы поместим сообщение об ошибке.
var form  = document.getElementById('setupForm');
var selectedNumber = document.getElementById('selectedNumber');
var instructions = document.getElementById('instructions');
var switcher = false;

var player1, player2;

var guess1 = [];
var guess2 = [];


form.addEventListener("submit", function (event) {
  if (selectedNumber.value.length != 3) {    
    console.error('Three digit numbers only!');
    
    
  } else {
      if (switcher){
          //second player
          player2 = selectedNumber.value;
          form.style.display = 'none';
          instructions.innerHTML = 'Lets play!';
          console.log('player1 value: '+ player1);
          console.log('player2 value: '+ player2);
      } else{
          //first player
            player1 = selectedNumber.value;
            selectedNumber.value = '';
            instructions.innerHTML = 'Player 2 number: ';
            switcher = true;
      }
  }
    event.preventDefault();
      

}, false);
