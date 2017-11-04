<template>
  <div>
    <div v-if="currentUser">
      {{ currentUser.email }}
    </div>
  </div>
</template>

<script>
import firebase from 'firebase';

export default {
  name: 'MedicalAdminDashboard',
  data () {
    return {
      currentUser:false
    }
  },
  methods:{

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
  }

}
</script>
