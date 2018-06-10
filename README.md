# Progress Dialog With Count Down

---------------
How To Use

//add pemission for heart rate monitor

```java 

       //Add to your module App
     implementation 'com.github.imabhisheknath:HoyoGameProgressbar:v1.5-beta'
  
  
    //initialize the Progress Dialog
   ProgressDialogTheme  progressDialog = new ProgressDialogTheme(Context);

   //set Listner for status monitor
   progressDialog.setResponseListener(listner);

    //you can set time out  time
   progressDialog.setTimeOutTime(6000);
   
     //for disable voice   count down
   progressDialog.DisableVoice();
   
   //for unable count down animation 
   progressDialog.setCountDownTime(5L);;  //pass starting count

  //enable time remaing for timeout
   progressDialog.showTimeout();    
  
   //call  countdown automatically
  progressDialog.enableAutoCountDown();
  
    //for displaying call count down manually    //   progressDialog.Prepared(); must executed  
   progressDialog.startCountDown();            
  
    //for starting up 
  progressDialog.start();


   //for all status and setting prepared
 progressDialog.Prepared();

  //for any status or setting failed 
progressDialog.notPrepared();



// Monitor Status BY
  ProgressDialogTheme.onProgressDialogTimeoutListner listner = new ProgressDialogTheme.onProgressDialogTimeoutListner() {
        @Override
        public void onTimeOut() {
        
        //trigger after countdown
        }

        @Override
        public void onCancel() {

           //trigger if  call  progressDialog.notPrepared();
        }

        @Override
        public void onSucess() {

           //trigger when  progressDialog.Prepared();  and after countdown over if enable
       
        }

        @Override
        public void secoundsForTimeout(long sec) {
           // getting secounds remaing for countdown
        }

        @Override
        public void onForcingCountdownstart() {
           
           calling  forcefully     progressDialog.startCountDown(); 
           
           
           
           
        }

    };


  
  

```
