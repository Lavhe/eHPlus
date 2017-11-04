<template>
<div>

  <div class="content">
    <div id="project-dashboard" class="page-layout simple right-sidebar tabbed">

      <div class="page-content-wrapper">
        <!-- HEADER -->
        <div class="page-header bg-secondary text-auto d-flex flex-column justify-content-between px-6 pt-4 pb-0">

          <div class="row no-gutters align-items-start justify-content-between flex-nowrap">

            <div>
              <span class="h2">Welcome back, John!</span>
            </div>

            <button type="button" class="sidebar-toggle-button btn btn-icon d-block d-xl-none" data-fuse-bar-toggle="dashboard-project-sidebar" aria-label="Toggle sidebar">
                      <i class="icon icon-menu"></i>
                  </button>
          </div>

        </div>
        <!-- / HEADER -->

        <!-- CONTENT -->
        <div class="page-content">

          <ul class="nav nav-tabs" id="myTab" role="tablist">

            <li class="nav-item">
              <a class="nav-link btn active" id="home-tab" data-toggle="tab" href="#home-tab-pane" role="tab" aria-controls="home-tab-pane" aria-expanded="true">Home</a>
            </li>

            <li class="nav-item">
              <a class="nav-link btn" id="budget-summary-tab" data-toggle="tab" href="#budget-summary-tab-pane" role="tab" aria-controls="budget-summary-tab-pane">Budget Summary</a>
            </li>

            <li class="nav-item">
              <a class="nav-link btn" id="team-members-tab" data-toggle="tab" href="#team-members-tab-pane" role="tab" aria-controls="team-members-tab-pane">Team Members</a>
            </li>
          </ul>

          <div class="tab-content">
            <div class="tab-pane fade show active" id="home-tab-pane" role="tabpanel" aria-labelledby="home-tab">
              <div class="widget-group row">
                <!-- WIDGET 2 -->
                <div v-if="selectedOption == 1" class="col-12 col-sm-12 col-xl-9 p-9 row">

                  <!-- CONTENT -->
                  <div class="content">
                    <div id="contacts" class="page-layout simple left-sidebar-floating">
                      <div class="page-content p-4 p-sm-6">
                        <!-- CONTACT LIST -->
                        <div class="contacts-list card">
                          <!-- CONTACT LIST HEADER -->
                          <div class="contacts-list-header p-6">
                            <div class="row no-gutters align-items-center justify-content-between">
                              <div class="list-title text-muted">
                                All contacts ({{ PendingAppointments.length }})
                              </div>
                            </div>
                          </div>
                          <!-- / CONTACT LIST HEADER -->
                          <!-- CONTACT ITEM -->
                          <div v-for="appointment in PendingAppointments" class="contact-item ripple row no-gutters align-items-center py-2 px-3 py-sm-4 px-sm-6">

                            <img class="avatar mx-4" alt="Abbott" src="static/assets/images/avatars/Abbott.jpg" />

                            <div class="col text-truncate font-weight-bold">Abbott Keitch</div>


                            <div class="col email text-truncate px-1 d-none d-xl-flex">
                              {{ appointment.Description }}
                            </div>

                            <div class="col phone text-truncate px-1 d-none d-xl-flex">
                              {{ appointment.PrefferedHospital }}
                            </div>

                            <div class="col job-title text-truncate px-1 d-none d-sm-flex">
                              {{ appointment.PrefferedDate }}
                            </div>

                            <div class="col company text-truncate px-1 d-none d-sm-flex">
                              {{ appointment.Type }}
                            </div>

                            <div class="col-auto actions">
                              <div class="row no-gutters">
                                <button type="button" class="btn btn-outline-success">
                                    Accept
                                </button>
                                <button type="button" class="btn btn-outline-danger">
                                    Decline
                                </button>
                              </div>
                            </div>
                          </div>
                          <!-- CONTACT ITEM -->
                        </div>
                      </div>
                    </div>
                  </div>

                  <a role="button" v-on:click="selectedOption = 0">Restart</a>
                </div>

                <div v-if="selectedOption == 2" class="col-12 col-sm-12 col-xl-9 p-9 row">
                  Option two
                  <a role="button" v-on:click="selectedOption = 0">Restart</a>
                </div>

                <div v-if="selectedOption == 3" class="col-12 col-sm-12 col-xl-9 p-9 row">
                  Option three
                  <a role="button" v-on:click="selectedOption = 0">Restart</a>
                </div>

                <div v-if="selectedOption == 0" class="col-12 col-sm-12 col-xl-9 p-9 row">
                  <div class="col-12 col-sm-6 col-xl-4 p-4">

                    <div class="widget widget2 card hoverable" role="button" v-on:click="selectedOption = 1">

                      <div class="widget-header pl-4 pr-2 row no-gutters align-items-center justify-content-between">

                        <div class="col">
                          <span class="h6">Pending Appointments</span>
                        </div>

                        <button type="button" class="btn btn-icon">
                            <i class="icon icon-dots-vertical"></i>
                        </button>
                      </div>

                      <div class="widget-content pt-2 pb-8 d-flex flex-column align-items-center justify-content-center">
                        <div class="title text-danger">{{ PendingAppointments.length }}</div>
                        <div class="sub-title h6 text-muted">Appointments</div>
                      </div>

                      <div class="widget-footer p-4 bg-light row no-gutters align-items-center">
                        <span class="text-muted">Today : </span>
                        <span class="ml-2">2</span>
                      </div>
                    </div>
                  </div>
                  <!-- / WIDGET 2 -->

                  <!-- WIDGET 3 -->
                  <div class="col-12 col-sm-6 col-xl-4 p-4">

                    <div class="widget widget3 card hoverable" role="button" v-on:click="selectedOption = 2">

                      <div class="widget-header pl-4 pr-2 row no-gutters align-items-center justify-content-between">

                        <div class="col">
                          <span class="h6">Patients you have seen</span>
                        </div>

                        <button type="button" class="btn btn-icon">
                            <i class="icon icon-dots-vertical"></i>
                        </button>

                      </div>

                      <div class="widget-content pt-2 pb-8 d-flex flex-column align-items-center justify-content-center">
                        <div class="title text-warning">{{ Patients.length }}</div>
                        <div class="sub-title h6 text-muted">Patients</div>
                      </div>

                      <div class="widget-footer p-4 bg-light row no-gutters align-items-center">
                        <span class="text-muted">Closed today:</span>
                        <span class="ml-2">0</span>
                      </div>
                    </div>
                  </div>
                  <!-- / WIDGET 3 -->

                  <!-- WIDGET 4 -->
                  <div class="col-12 col-sm-6 col-xl-4 p-4">

                    <div class="widget widget4 card hoverable" role="button" v-on:click="selectedOption = 3">

                      <div class="widget-header pl-4 pr-2 row no-gutters align-items-center justify-content-between">

                        <div class="col">
                          <span class="h6">Due Appointments</span>
                        </div>

                        <button type="button" class="btn btn-icon">
                            <i class="icon icon-dots-vertical"></i>
                        </button>

                      </div>

                      <div class="widget-content pt-2 pb-8 d-flex flex-column align-items-center justify-content-center">
                        <div class="title text-info">{{ DueAppointments.length }}</div>
                        <div class="sub-title h6 text-muted">Appointments</div>
                      </div>

                      <div class="widget-footer p-4 bg-light row no-gutters align-items-center">
                        <span class="text-muted">Implemented:</span>
                        <span class="ml-2">8</span>
                      </div>
                    </div>
                  </div>
                </div>
                <!-- / WIDGET 4 -->
                <div class="about col-12 col-sm-6 col-xl-3 p-3">

                  <div class="profile-box info-box general card mb-4">

                    <header class="h6 bg-secondary text-auto p-4">
                      <div class="title">General Information</div>
                    </header>

                    <div class="content p-4">

                      <div class="info-line mb-6">
                        <div class="title font-weight-bold mb-1">Gender</div>
                        <div class="info">Female</div>
                      </div>

                      <div class="info-line mb-6">
                        <div class="title font-weight-bold mb-1">Birthday</div>
                        <div class="info">12.01.1987</div>
                      </div>

                      <div class="info-line mb-6">
                        <div class="title font-weight-bold mb-1">Locations</div>

                        <div class="info location">
                          <span>Istanbul, Turkey</span>
                          <i class="icon-map-marker s-4"></i>
                        </div>

                        <div class="info location">
                          <span>New York, USA</span>
                          <i class="icon-map-marker s-4"></i>
                        </div>

                      </div>

                      <div class="info-line">
                        <div class="title font-weight-bold mb-1">About Me</div>
                        <div class="info">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis eget pharetra felis, sed ullamcorper dui. Sed et elementum neque. Vestibulum pellente viverra ultrices. Etiam justo augue, vehicula ac gravida a, interdum sit amet nisl.
                          Integer vitae nisi id nibh dictum mollis in vitae tortor.</div>
                      </div>

                    </div>
                  </div>

                  <div class="profile-box info-box work card mb-4">

                    <header class="h6 bg-secondary text-auto p-4">
                      <div class="title">Work</div>
                    </header>

                    <div class="content p-4">

                      <div class="info-line mb-6">
                        <div class="title font-weight-bold mb-1">Occupation</div>
                        <div class="info">Developer</div>
                      </div>

                      <div class="info-line mb-6">
                        <div class="title font-weight-bold mb-1">Skills</div>
                        <div class="info">C#, PHP, Javascript, Angular, JS, HTML, CSS</div>
                      </div>

                      <div class="info-line mb-6">
                        <div class="title font-weight-bold mb-1">Jobs</div>
                        <table class="info jobs">

                          <tr class="job">
                            <td class="company font-weight-bold pr-4">Self-Employed</td>
                            <td class="date">2010 - Now</td>
                          </tr>

                          <tr class="job">
                            <td class="company font-weight-bold pr-4">Google</td>
                            <td class="date">2008 - 2010</td>
                          </tr>

                        </table>
                      </div>

                    </div>
                  </div>

                  <div class="profile-box info-box contact card mb-4">

                    <header class="h6 bg-secondary text-auto p-4">
                      <div class="title">Contact</div>
                    </header>

                    <div class="content p-4">

                      <div class="info-line mb-6">
                        <div class="title font-weight-bold mb-1">Address</div>
                        <div class="info">Ut pharetra luctus est quis sodales. Duis nisi tortor, bibendum eget tincidunt, aliquam ac elit. Mauris nec euismod odio.</div>
                      </div>

                      <div class="info-line mb-6">
                        <div class="title font-weight-bold mb-1">Tel.</div>

                        <div class="info">
                          <span>+6 555 6600</span>
                        </div>

                        <div class="info">
                          <span>+9 555 5255</span>
                        </div>

                      </div>

                      <div class="info-line mb-6">
                        <div class="title font-weight-bold mb-1">Website</div>

                        <div class="info">
                          <span>withinpixels.com</span>
                        </div>

                      </div>

                      <div class="info-line mb-6">
                        <div class="title font-weight-bold mb-1">Emails</div>

                        <div class="info" ng-repeat="email in vm.about.contact.emails">
                          <span>mail@withinpixels.com</span>
                        </div>

                        <div class="info" ng-repeat="email in vm.about.contact.emails">
                          <span>mail@creapond.com</span>
                        </div>

                      </div>

                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
</template>

<style scoped>
.hoverable {
  cursor: pointer;
}
</style>

<script>
import firebase from 'firebase';

export default {
  name: 'MedicalprofessionalDashboard',
  data() {
    return {
      currentUser: false,
      selectedOption: 0,
      PendingAppointments: [{
        UserID: '1290',
        Description: 'I have a tooth ache',
        PrefferedHospital: 'Tshilidzini',
        PrefferedDate: "DateTime.now()",
        Type: 'CheckUp',
      }, {
        UserID: '2290',
        Description: 'I think i am pregnant',
        PrefferedHospital: 'Netcare',
        PrefferedDate: "DateTime.now()",
        Type: 'CheckUp',
      }],
      DueAppointments: [{
        PendingAppointmentID: '1290',
        DateTime: "DateTime.now()",
        DoctorID: '3456346',
        Hospital: 'Netcare',
      }],
      Patients: [{
        Name: 'joe'
      }],
    }
  },
  methods: {

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
