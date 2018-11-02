# CaptchaGame
Sample Project demonstarating use of ViewModel and Kotlin

## Catcha Game Flow :

1) When the game starts user is presented with a captcha of medium difficulty of level 3. If the answers is correct, next captcha is presented with difficulty level increased by 1 i.e. 4 and if the answer is wrong, next question will be of difficulty decreased by 1 i.e. 2. After every question, depending on the answer, difficulty level of the next question is either incremented by 1 if answer is correct or decremented by 1 if answer is wrong.

2) There is a timer running on each captcha screen as well. If the timer expires before user submits the answer, it’ll be treated as wrong answer and difficulty level will be decreased by 1 for next captcha. 

3) The user will be presented with 5 captchas max.

4) You have a pool of 5 captchas for each difficulty level, so none of the captchas should be repeated.

5) Captchas should be selected randomly from pool of images for required difficulty level.

6) There is a game over screen after user has attempted all the captchas. In this screen user should be shown all the captchas that he/she attempted in a list and whether corresponding answer was correct or wrong for each of them.
