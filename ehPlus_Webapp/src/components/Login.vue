<template>
<div>
  <div class="content">
    <div id="login" class="p-8">
      <div class="form-wrapper md-elevation-8 p-8">
        <div class="logo bg-secondary">
          <span>F</span>
        </div>

        <div class="title mt-4 mb-8">Log in to your account</div>

        <form novalidate>

          <div class="form-group mb-4">
            <input v-model="UserEmail" type="email" class="form-control" id="loginFormInputEmail" aria-describedby="emailHelp" placeholder=" " />
            <label for="loginFormInputEmail">Email address</label>
          </div>

          <div class="form-group mb-4">
            <input v-model="UserPassword" type="password" class="form-control" id="loginFormInputPassword" placeholder="Password" />
            <label for="loginFormInputPassword">Password</label>
          </div>

          <div class="remember-forgot-password row no-gutters align-items-center justify-content-between pt-4">

            <div class="form-check remember-me mb-4">
              <label class="form-check-label">
                    <input type="checkbox" class="form-check-input" aria-label="Remember Me"/>
                    <span class="checkbox-icon"></span>
                    <span class="form-check-description">Remember Me</span>
              </label>
            </div>

            <a href="#" class="forgot-password text-secondary mb-4">Forgot Password?</a>
          </div>

          <div v-if="LoginError.length > 0" class="mt-8 mb-6 mx-auto">
            <span class="text mr-sm-2 text-danger">{{ LoginError }}</span>
          </div>

          <a type="button" role="button" v-on:click="login" class="submit-button btn btn-block btn-secondary my-4 mx-auto" aria-label="LOG IN">
                  LOG IN
          </a>

        </form>

        <div class="register d-flex flex-column flex-sm-row align-items-center justify-content-center mt-8 mb-6 mx-auto">
          <span class="text mr-sm-2">Don't have an account?</span>
          <a class="link text-secondary" href="pages-auth-register.html">Create an account</a>
        </div>

      </div>
    </div>
  </div>
</div>
</template>

<script>
import firebase from 'firebase'

export default {
  name: 'Login',
  data() {
    return {
      UserEmail:'',
      UserPassword:'',
      LoginError:''
    }
  },
  methods: {
    login() {
      var self = this;
      self.LoginError = "";
      firebase.auth().signInWithEmailAndPassword(self.UserEmail,self.UserPassword).then(
        function(user) {
          if(!user){
            self.LoginError = "Error can not sign you in, Try again later";
          }
        },
        function(err) {
          if(err.message){
            self.LoginError = err.message;
          }else{
            self.LoginError = "Error can not sign you in, Try again later";
          }
        }
      );
    },
  },
  mounted() {
    //do something after mounting vue instance
    var self = this;
    firebase.auth().onAuthStateChanged(
      function(user) {
        if (user) {
          firebase.database().ref('/User/' + user.uid + "/role").once('value').then(function(role) {
            if (role.val() == 1) {
              //  Medical practitioner
              self.$router.push({
                name: 'MedicalprofessionalDashboard',
                params: {
                  userId: user.uid
                }
              })
            } else if (role.val() == 2) {
              // Admin
              self.$router.push({
                name: 'MedicalAdminDashboard',
                params: {
                  userId: user.uid
                }
              })
            }
          });
        } else {

        }
      });
  }
}
</script>
