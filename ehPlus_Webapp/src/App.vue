<template>
<div id="app">

  <nav id="toolbar" class="fixed-top bg-white">

    <div class="row no-gutters align-items-center flex-nowrap">

      <div class="col">

        <div class="row no-gutters align-items-center flex-nowrap">



          <button type="button" v-on:click="$router.push({name:'/'})" class="btn btn-icon d-block d-lg-none">
               <i class="icon icon-home"></i>
           </button>

          <div class="toolbar-separator d-block d-lg-none"></div>




          <div class="shortcuts-wrapper row no-gutters align-items-center px-0 px-sm-2">

            <div class="shortcuts row no-gutters align-items-center d-none d-md-flex">
              <a href="/" class="btn btn-icon mx-1">
                <div class="" style="background-color:transparent !important">
                  <img class="avatar" src="static/assets/logo.png" style="background-color:transparent !important">
                </div>
              </a>
            </div>

          </div>

          <div class="toolbar-separator"></div>

        </div>
      </div>

      <div class="col-auto">

        <div class="row no-gutters align-items-center justify-content-end">

          <div class="user-menu-button dropdown" v-if="currentUser">

            <div class="dropdown-toggle ripple row align-items-center no-gutters px-2 px-sm-4" role="button" id="dropdownUserMenu" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
              <div class="avatar-wrapper">
                <img class="avatar" src="static/assets/images/avatars/profile.jpg">
                <i class="status text-green icon-checkbox-marked-circle s-4"></i>
              </div>
              <span class="username mx-3 d-none d-md-block">{{ currentUser.email }}</span>
            </div>

            <div class="dropdown-menu" aria-labelledby="dropdownUserMenu">

              <a class="dropdown-item" role="button" v-on:click="MyProfile" href="#">
                <div class="row no-gutters align-items-center flex-nowrap">
                  <i class="icon-account"></i>
                  <span class="px-3">My Profile</span>
                </div>
              </a>

              <div class="dropdown-divider"></div>

              <a class="dropdown-item" role="button" v-on:click="logOut" href="#">
                <div class="row no-gutters align-items-center flex-nowrap">
                  <i class="icon-logout"></i>
                  <span class="px-3">Logout</span>
                </div>
              </a>
            </div>
          </div>
          <button v-if="!currentUser" type="button" role="button" v-on:click="Login" class="search-button btn btn-icon">
                  Log in
          </button>
        </div>
      </div>
    </div>
  </nav>
  <router-view/>
</div>
</template>

<style media="screen">
  #app{
    padding-top:60px;
  }
  .redBg{
    background-color: #EF3A4A;
  }
  .redText{
    color:#EF3A4A;
  }
</style>

<script>
import firebase from 'firebase'

export default {
  name: 'app',
  data(){
    return {
      currentUser:false
    }
  },
  methods:{
    logOut(){
      firebase.auth().signOut().then(function() {
        this.$router.push({
          name: 'Home'
        });
        window.location.href = '/';
      }).catch(function(error) {

      });
    },
    Login(){
      this.$router.push({
        name: 'Login'
      });
    },
    MyProfile() {
      var self = this;
      if (self.currentUser) {
        firebase.database().ref('/User/' + self.currentUser.uid + "/role").once('value').then(function(role) {
          console.log(role.val() + " value");
          if (role.val() == 1) {
            //  Medical practitioner
            self.$router.push({
              name: 'MedicalprofessionalDashboard',
              params: {
                userId: self.currentUser.uid
              }
            })
          } else if (role.val() == 2) {
            // Admin
            self.$router.push({
              name: 'MedicalAdminDashboard',
              params: {
                userId: self.currentUser.uid
              }
            })
          }
        });
      } else {

      }
    }
  },
  mounted() {
    //do something after mounting vue instance
    var self = this;
    firebase.auth().onAuthStateChanged(
      function(user) {
        if (user) {
          self.currentUser = user;
        }
      });
      console.log(this.currentUser);
  }

}
</script>
