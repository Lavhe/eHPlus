<template>
  <div>

      <div class="content">
        <div id="project-dashboard" class="page-layout simple right-sidebar tabbed">

          <div class="page-content-wrapper">
            <!-- CONTENT -->
            <div class="page-content">

              <ul class="nav nav-tabs" id="myTab" role="tablist">

                <li class="nav-item">
                  <a class="nav-link btn active" id="home-tab" data-toggle="tab" href="#home-tab-pane" role="tab" aria-controls="home-tab-pane" aria-expanded="true">Home</a>
                </li>

                <li class="nav-item">
                  <a class="nav-link btn" id="profile-tab" data-toggle="tab" href="#profile-tab-pane" role="tab" aria-controls="profile-tab-pane">Profile</a>
                </li>

                <li class="nav-item">
                  <a class="nav-link btn" id="team-members-tab" data-toggle="tab" href="#team-members-tab-pane" role="tab" aria-controls="team-members-tab-pane">Team Members</a>
                </li>

                <li class="nav-item">
                  <a class="nav-link btn" id="profile-tab2" data-toggle="tab" href="#profile-tab-pane2" role="tab" aria-controls="profile-tab-pane2">Profile22</a>
                </li>

                <li class="nav-item">
                  <a class="nav-link btn" id="team-members-tab2" data-toggle="tab" href="#team-members-tab-pane2" role="tab" aria-controls="team-members-tab-pane2">Team Members22</a>
                </li>
              </ul>

              <div class="tab-content">
                <div class="tab-pane fade show active" id="home-tab-pane" role="tabpanel" aria-labelledby="home-tab">
                  TAb1
                </div>
                <div class="tab-pane fade show" id="profile-tab-pane" role="tabpanel" aria-labelledby="profile-tab">
                  TAb21
                </div>
              </div>
            </div>
          </div>
        </div>
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
