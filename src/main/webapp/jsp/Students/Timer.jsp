<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script>
function startTimer(timeLeft) {
  var countdown = setInterval(function() {
    var minutesLeft = Math.floor(timeLeft / 60);
    var secondsLeft = timeLeft % 60;
    var timerText = minutesLeft + ":" + (secondsLeft < 10 ? "0" : "") + secondsLeft;
    document.getElementById("timer").innerHTML = timerText;
    timeLeft--;
    if (timeLeft < 0) {
      clearInterval(countdown);
      document.getElementById("timer").innerHTML = "Time's up!";
      document.getElementById("examForm").submit();
    }
  }, 1000);
}
</script>

<div id="timer"></div> 

