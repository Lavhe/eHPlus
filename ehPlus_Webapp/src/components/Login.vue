<template>
<div class="hello">
  <h1>{{ msg }}</h1>
  <h2>Log in</h2>
</div>
</template>

<script>
import firebase from 'firebase';

export default {
  name: 'Login',
  data() {
    return {
      msg: 'You are logged in '
    }
  },
  mounted() {
    //do something after mounting vue instance
    onAuthStateChanged(function(user) {
        if (user) {
          // User is signed in.
          var displayName = user.role;
          var email = user.email;
          var emailVerified = user.emailVerified;
          var photoURL = user.photoURL;
          var isAnonymous = user.isAnonymous;
          var uid = user.uid;
          var providerData = user.providerData;
          // ...
        } else {
          // User is signed out.
          // ...
        }
      }
    }
</script>
