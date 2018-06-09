# Progress Dialog With Count Down

---------------
How To Use

//add pemission for heart rate monitor

```java 

//Add to your module App
  compile 'com.github.imabhisheknath:ProgressDialog:v1.0-beta'
  
  
  //initialize the Progress Dialog
  
 ProgressDialogTheme  progressDialog = new ProgressDialogTheme(Context);

//set Listner for status monitor
 progressDialog.setResponseListener(listner);

//you can set time out  time
  progressDialog.setTimeOutTime(5000);



//for unable count down animation 
 progressDialog.setCountDownTime(5L);;  //pass starting count

  progressDialog.enableCountText();      //enable total time remaing which is present for textview
  
  
  progressDialog.callCountDownAutoMatically();  //call  countdown automatically
  
  
    progressDialog.startCountDown();            //call count down manually  location sucess call neccesarry before calling this
  
  
  //for starting up 
  progressDialog.start();


//for receiving our Location Sucess Call
progressDialog.LocationSucess();

// if Location Failed call
progressDialog.LocationFailed();





// Monitor Status BY

 ProgressDialogTheme.onProgressDialogTimeoutListner listner = new ProgressDialogTheme.onProgressDialogTimeoutListner() {
        @Override
        public void onTimeOut() {

           //called when time out 
            tvTitle.setText("timout");
        }

        @Override
        public void onCancelbyLocationFailed() {

            tvTitle.setText("cancel");
            //call when location fails
        }

        @Override
        public void onAllSet() {
        
        //called when all is ready

            tvTitle.setVisibility(View.VISIBLE);
            tvTitle.setText("ready");
        }

        @Override
        public void secoundRemainngforCountDown(long sec) {
            tvTitle.setText("s" + sec);
            Log.e("sec", "" + sec);
            
            //display remaing time
        }

        @Override
        public void onInvalidCountdownStartdueToLocation() {
            tvTitle.setText("invalid Location");
            
            
            //when    progressDialog.startCountDown();   called before location sucess
        }

    };


  
  

```
