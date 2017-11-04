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
          </ul>

          <div class="tab-content">
            <div class="tab-pane fade show active" id="home-tab-pane" role="tabpanel" aria-labelledby="home-tab">
              <div class="widget-group row">
                <!-- WIDGET 2 -->
                <div v-if="selectedOption != 0">
                  <a role="button" class="pull-right right btn btn-icon" v-on:click="selectedOption = 0">
                      <i class="icon icon-close"></i>
                    </a>
                </div>
                <div v-if="selectedOption == 1" class="col-12 col-sm-12 col-xl-12 row">
                  <div class="page-content col-12 col-lg-9">
                    <!-- CONTACT LIST -->
                    <div class="contacts-list card">
                      <!-- CONTACT LIST HEADER -->
                      <div class="contacts-list-header p-6">
                        <div class="row no-gutters align-items-center justify-content-between">
                          <div class="list-title redText">
                            Pending Appointments ({{ PendingAppointments.length }})
                          </div>
                        </div>
                      </div>
                      <!-- / CONTACT LIST HEADER -->
                      <!-- CONTACT ITEM -->
                      <div v-for="appointment in PendingAppointments" class="contact-item hoverable ripple row no-gutters align-items-center py-2 px-3 py-sm-4 px-sm-6">

                        <img class="avatar mx-4" alt="Abbott" :src="appointment.patient.pp" />

                        <div class="col text-truncate font-weight-bold">{{appointment.patient.name}}</a>
                        </div>

                        <div class="col email text-truncate px-1 d-none d-xl-flex">
                          {{ appointment.description }}
                        </div>

                        <div class="col phone text-truncate px-1 d-none d-xl-flex">
                          {{ appointment.hospital }}
                        </div>

                        <div class="col job-title text-truncate px-1 d-none d-sm-flex">
                          {{ appointment.date }}
                        </div>

                        <div class="col job-title text-truncate px-1 d-none d-sm-flex">
                          {{ appointment.timeStart }} - {{ appointment.timeEnd }}
                        </div>

                        <div class="col company text-truncate px-1 d-none d-sm-flex">
                          {{ appointment.type }}
                        </div>

                        <div class="col-auto actions">
                          <div class="row no-gutters">
                            <button type="button" v-on:click="ApproveAppointment(appointment)" class="btn btn-outline-success">
                                      Accept
                                  </button>
                              <button type="button" v-on:click="DeclineAppointment(appointment)" class="btn btn-outline-danger">
                                      Decline
                                  </button>
                          </div>
                        </div>
                      </div>
                      <!-- CONTACT ITEM -->
                    </div>
                  </div>

                  <div class="col-12 col-lg-3">

                    <div class="widget widget6 card">

                      <div class="widget-header px-4 row no-gutters align-items-center justify-content-between">

                        <div class="col">
                          <span class="h6 redText">Patient's Profile</span>
                        </div>

                        <div class="">
                          <select id="widget6-option-select" class="h6 custom-select">
                                  <option selected="" value="TW">Basic</option>
                                  <option value="LW">In detail</option>
                              </select>
                        </div>

                      </div>

                      <div class="widget-content">

                        <div class="row no-gutters">

                          <div class="col-12">

                            <ul class="list-group dense">

                              <li class="list-group-item two-line">
                                <i class="icon icon-account"></i>
                                <div class="list-item-content">
                                  <h3>Full name</h3>
                                  <p>{{ selectedPatient.name }}</p>
                                </div>
                              </li>

                              <li class="list-group-item two-line">
                                <i class="icon icon-account-circle"></i>
                                <div class="list-item-content">
                                  <h3>ID number</h3>
                                  <p>{{ selectedPatient.id }}</p>
                                </div>
                              </li>

                              <li class="list-group-item two-line">
                                <i class="icon icon-medical-bag"></i>
                                <div class="list-item-content">
                                  <h3>Blood Type</h3>
                                  <p>{{ selectedPatient.bloodType }}</p>
                                </div>
                              </li>

                              <li class="list-group-item two-line">
                                <i class="icon icon-calendar-clock"></i>
                                <div class="list-item-content">
                                  <h3>Recent Checkup date</h3>
                                  <p>{{ selectedPatient.lastCheckupDate }}</p>
                                </div>
                              </li>
                            </ul>


                          </div>

                          <div class="divider col-12"></div>

                          <div id="added-tasks" class="col-6 d-flex flex-column align-items-center justify-content-center py-4">

                            <div class="count h2 redText">{{ selectedPatient.PendingAppointments }}</div>

                            <div class="count-title">Pending Appointments</div>

                          </div>

                          <div id="completed-tasks" class="col-6 d-flex flex-column align-items-center justify-content-center py-4">

                            <div class="count h2 redText">{{ selectedPatient.hospitalVisits }}</div>

                            <div class="count-title">Total Hospital visits</div>

                          </div>

                        </div>
                      </div>
                    </div>
                  </div>
                </div>

                <div v-if="selectedOption == 2" class="col-12 col-sm-12 col-xl-12 row">
                  <div class="col-12 col-lg-3" v-for="patient in Patients">

                    <div class="widget widget6 card">

                      <div class="widget-header px-4 row no-gutters align-items-center justify-content-between">

                        <div class="col">
                          <span class="h6">Patient's Profile</span>
                        </div>

                        <div class="">
                          <select id="widget6-option-select" class="h6 custom-select">
                                    <option selected="" value="TW">Basic</option>
                                    <option value="LW">In detail</option>
                                </select>
                        </div>

                      </div>

                      <div class="widget-content">

                        <div class="row no-gutters">

                          <div class="col-12" v-if="showMedicalHistory != patient.uid">

                            <ul class="list-group dense">

                              <li class="list-group-item two-line">
                                <i class="icon icon-account"></i>
                                <div class="list-item-content">
                                  <h3>Full name</h3>
                                  <p>{{ patient.name }}</p>
                                </div>
                              </li>

                              <li class="list-group-item two-line">
                                <i class="icon icon-account-circle"></i>
                                <div class="list-item-content">
                                  <h3>ID number</h3>
                                  <p>{{ patient.id }}</p>
                                </div>
                              </li>

                              <li class="list-group-item two-line">
                                <i class="icon icon-medical-bag"></i>
                                <div class="list-item-content">
                                  <h3>Blood Type</h3>
                                  <p>{{ patient.bloodType }}</p>
                                </div>
                              </li>

                              <li class="list-group-item two-line">
                                <i class="icon icon-calendar-clock"></i>
                                <div class="list-item-content">
                                  <h3>Recent Checkup date</h3>
                                  <p>{{ patient.lastCheckupDate }}</p>
                                </div>
                              </li>
                            </ul>


                          </div>
                          <div class="" v-if="showMedicalHistory == patient.uid">
                            <a href="#" role="button" v-on:click="showMedicalHistory = false"><i class="icon icon-close"></i></a>

                            <ul class="list-group dense">

                              <li class="list-group-item two-line">
                                <i class="icon icon-account"></i>
                                <div class="list-item-content">
                                  <h3>Full name</h3>
                                  <p>{{ patient.name }}</p>
                                </div>
                              </li>

                              <li class="list-group-item three-line" v-for="MedicalHistory in patient.medicalHistories">
                                <i class="icon icon-calendar-clock"></i>
                                <div class="list-item-content">
                                  <h3>{{ MedicalHistory.description }}</h3>
                                  <h4>{{ MedicalHistory.date }} at {{ MedicalHistory.hospital }}</h4>
                                  <p>{{ MedicalHistory.feedback }}</p>
                                </div>
                              </li>
                            </ul>
                          </div>

                          <div class="divider col-12"></div>

                          <div class="col-auto actions" v-if="updateMedicalHistory != patient.uid && showMedicalHistory != patient.uid">
                            <div class="row no-gutters">
                              <button type="button" v-on:click="showMedicalHistory = patient.uid" class="btn btn-outline-danger">
                                    View Medical history
                              </button>
                              <button type="button" v-on:click="updateMedicalHistory = patient.uid" class="btn btn-outline-danger">
                                    Update Medical Record
                              </button>
                            </div>
                          </div>
                          <div v-if="updateMedicalHistory == patient.uid" class="col-auto actions">
                            <div class="row no-gutters">
                              <a href="#" role="button" v-on:click="updateMedicalHistory = false"><i class="icon icon-close"></i></a>
                              <div class="form-group">
                                <input v-model="NewAllergy" class="form-control" type="text" value="Artisanal kale" id="example-text-input" />
                                <label for="example-text-input">New Allergy</label>
                              </div>
                              <div>
                                <button type="button" v-on:click="saveAllergy" class="btn btn-outline-danger">
                                    Add {{ NewAllergy }}
                              </button>
                              </div>
                            </div>
                          </div>

                          <div id="added-tasks" class="col-6 d-flex flex-column align-items-center justify-content-center py-4">

                            <div class="count h2">{{ patient.PendingAppointments }}</div>

                            <div class="count-title">Pending Appointments</div>

                          </div>

                          <div id="completed-tasks" class="col-6 d-flex flex-column align-items-center justify-content-center py-4">

                            <div class="count h2">{{ patient.hospitalVisits }}</div>

                            <div class="count-title">Total Hospital visits</div>

                          </div>

                        </div>
                      </div>
                    </div>
                  </div>

                </div>

                <div v-if="selectedOption == 3" class="col-12 col-sm-12 col-xl-12 p-9 row">
                  <div class="page-content col-12 col-lg-12">
                    <!-- CONTACT LIST -->
                    <div class="contacts-list card">
                      <!-- CONTACT LIST HEADER -->
                      <div class="contacts-list-header p-6">
                        <div class="row no-gutters align-items-center justify-content-between">
                          <div class="list-title redText">
                            Due Appointments ({{ DueAppointments.length }})
                          </div>
                        </div>
                      </div>
                      <!-- / CONTACT LIST HEADER -->
                      <!-- CONTACT ITEM -->
                      <div v-for="appointment in DueAppointments" class="contact-item hoverable ripple row no-gutters align-items-center py-2 px-3 py-sm-4 px-sm-6">

                        <img class="avatar mx-4" alt="Abbott" :src="appointment.patient.pp" />

                        <div class="col text-truncate font-weight-bold">{{appointment.patient.name}}</a>
                        </div>

                        <div class="col email text-truncate px-1 d-none d-xl-flex">
                          {{ appointment.description }}
                        </div>

                        <div class="col phone text-truncate px-1 d-none d-xl-flex">
                          {{ appointment.hospital }}
                        </div>

                        <div class="col job-title text-truncate px-1 d-none d-sm-flex">
                          {{ appointment.date }}
                        </div>

                        <div class="col job-title text-truncate px-1 d-none d-sm-flex">
                          {{ appointment.timeStart }} - {{ appointment.timeEnd }}
                        </div>

                        <div class="col company text-truncate px-1 d-none d-sm-flex">
                          {{ appointment.type }}
                        </div>

                        <div class="col-auto actions">
                          <div class="row no-gutters" v-if="addFeedback == appointment.id">
                            <div class="form-group">
                              <input v-model="Feedback" class="form-control" type="text" value="Artisanal kale" id="example-text-input2" />
                              <label for="example-text-input2">FeedBack (Optional)</label>
                            </div>
                            <div>
                              <button type="button" v-on:click="saveFeedBack(appointment)" class="btn btn-outline-danger">
                                    Save Feedback
                              </button>
                            </div>
                          </div>
                          <div class="row no-gutters" v-if="addFeedback != appointment.id">
                            <button type="button" v-on:click="addFeedback = appointment.id" class="btn btn-outline-danger">
                                  Add Feedback
                            </button>
                          </div>
                        </div>
                      </div>
                      <!-- CONTACT ITEM -->
                    </div>
                  </div>

                </div>

                <div v-if="selectedOption == 4" class="row">

                  <div class="h5 mb-3">Pie Chart</div>
                  <div class="card p-12 mb-12">
                    <div id="pie-chart">
                      <svg></svg>
                    </div>
                  </div>
                </div>

                <div v-if="selectedOption == 0" class="col-12 col-sm-12 col-xl-12 row">
                  <div class="col-12 col-sm-6 col-xl-3">

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
                        <div class="title redText">{{ PendingAppointments.length }}</div>
                        <div class="sub-title h6 text-muted">Appointments</div>
                      </div>

                      <div class="widget-footer p-4 bg-light row no-gutters align-items-center">
                        <span class="text-muted">Today : </span>
                        <span class="ml-2">2</span>
                      </div>
                    </div>
                  </div>

                  <div class="col-12 col-sm-6 col-xl-3">

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
                        <div class="title redText">{{ DueAppointments.length }}</div>
                        <div class="sub-title h6 text-muted">Appointments</div>
                      </div>

                      <div class="widget-footer p-4 bg-light row no-gutters align-items-center">
                        <span class="text-muted">Implemented:</span>
                        <span class="ml-2">8</span>
                      </div>
                    </div>
                  </div>

                  <div class="col-12 col-sm-6 col-xl-3">

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


                  <div class="col-12 col-sm-6 col-xl-3">

                    <div class="widget widget3 card hoverable" role="button" v-on:click="selectedOption = 4">

                      <div class="widget-header pl-4 pr-2 row no-gutters align-items-center justify-content-between">

                        <div class="col">
                          <span class="h6">Hospitals you have visited</span>
                        </div>

                        <button type="button" class="btn btn-icon">
                              <i class="icon icon-dots-vertical"></i>
                        </button>

                      </div>

                      <div class="widget-content pt-2 pb-8 d-flex flex-column align-items-center justify-content-center">
                        <div class="title text-warning">{{ Hospitals.length }}</div>
                        <div class="sub-title h6 text-muted">Hospitals</div>
                      </div>

                      <div class="widget-footer p-4 bg-light row no-gutters align-items-center">
                        <span class="text-muted">Province : </span>
                        <span class="ml-2">Gauteng</span>
                      </div>
                    </div>
                  </div>

                  <div class="col-12 col-sm-6 col-xl-3">
                    <div class="profile-box latest-activity card">
                      <header class="row no-gutters align-items-center justify-content-between redBg text-auto p-4">

                        <div class="title text-white h6">Closest upcoming appointments</div>

                      </header>
                      <div class="content activities p-4">
                        <div class="activity row no-gutters py-4 hoverable">
                          <ul class="list-group dense">
                            <li class="list-group-item three-line" v-if="DueAppointments.length > i" v-for="i in 3">
                              <div class="avatar">
                                <img src="static/assets/images/avatars/1.jpg">
                              </div>
                              <div class="list-item-content">
                                <h3>{{ DueAppointments[i].PendingAppointmentID }}</h3>
                                <h4>{{ DueAppointments[i].Hospital }}</h4>
                                <p>{{ DueAppointments[i].DateTime }}</p>
                              </div>
                            </li>
                          </ul>
                        </div>
                      </div>
                    </div>
                  </div>

                </div>
              </div>
            </div>
            <div class="tab-pane fade show" id="profile-tab-pane" role="tabpanel" aria-labelledby="profile-tab">
              <!-- / WIDGET 4 -->
              <div class="row">
                <div class="about col-12 col-sm-4 col-xl-3 p-3">

                  <div class="profile-box info-box general card mb-4">
                    <img src="static/assets/images/avatars/profile.jpg" style="height:100%;width:100%" class="img-fluid img-responsive" alt="">
                  </div>

                </div>
                <div class="about col-12 col-sm-4 col-xl-3 p-3">

                  <div class="profile-box info-box general card mb-4">

                    <header class="h6 redBg text-auto p-4">
                      <div class="title text-white">General Information</div>
                    </header>

                    <div class="content p-4">

                      <div class="info-line mb-6">
                        <div class="title font-weight-bold mb-1">Surname and Name</div>

                        <div class="info">
                          <span>Sirwali</span>
                        </div>

                        <div class="info">
                          <span>Mulavhe Joseph</span>
                        </div>
                      </div>


                      <div class="info-line mb-6">
                        <div class="title font-weight-bold mb-1">ID number</div>
                        <div class="info">96081521474008</div>
                      </div>

                      <div class="info-line mb-6">
                        <div class="title font-weight-bold mb-1">Locations</div>

                        <div class="info location">
                          <span>Ekurhuleni , Johannesburg</span>
                          <i class="icon-map-marker s-4"></i>
                        </div>

                      </div>

                    </div>
                  </div>

                </div>
                <div class="about col-12 col-sm-4 col-xl-3 p-3">

                  <div class="profile-box info-box work card mb-4">

                    <header class="h6 redBg text-auto p-4">
                      <div class="title text-white">Work</div>
                    </header>

                    <div class="content p-4">

                      <div class="info-line mb-6">
                        <div class="title font-weight-bold mb-1">Occupation</div>
                        <div class="info">Doctor</div>
                      </div>

                      <div class="info-line mb-6">
                        <div class="title font-weight-bold mb-1">Skills</div>
                        <div class="info">Dentist, GP</div>
                      </div>

                      <div class="info-line mb-6">
                        <div class="title font-weight-bold mb-1">Hospitals</div>

                        <div class="info">
                          <span>Tshilidzini</span>
                        </div>

                        <div class="info">
                          <span>Netcare</span>
                        </div>

                      </div>

                    </div>
                  </div>

                </div>
                <div class="about col-12 col-sm-4 col-xl-3 p-3">

                  <div class="profile-box info-box contact card mb-4">

                    <header class="h6 redBg text-auto p-4">
                      <div class="title text-white">Contact</div>
                    </header>

                    <div class="content p-4">
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
                        <div class="title font-weight-bold mb-1">Email</div>
                        <div class="info">mulavhe@gmail.com</div>
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
      showMedicalHistory: false,
      updateMedicalHistory: false,
      NewAllergy: '',
      Feedback: '',
      addFeedback: false,
      currentUser: false,
      selectedPatient: {
        name: "Sirwali Joseph",
        id: 8909056037087,
        bloodType: "B",
        lastCheckupDate: "date.now()",
        PendingAppointments: 10,
        hospitalVisits: 9
      },
      selectedOption: 0,
      PendingAppointments: [],
      DueAppointments: [],
      Patients: [{
          name: "Sirwali Joseph",
          uid: 'safasgsaga',
          id: 8909056037087,
          bloodType: "B",
          lastCheckupDate: "date.now()",
          PendingAppointments: 10,
          hospitalVisits: 9,
          medicalHistories: [{
            date: "dateTime.now()",
            hospital: "Tshilidzini",
            description: "I have a flue",
            feedback: "Your flue will be ohk"
          }, {
            date: "dateTime.now()",
            hospital: "Tshilidzini",
            description: "I have a flue",
            feedback: "Your flue will be ohk"
          }, {
            date: "dateTime.now()",
            hospital: "Tshilidzini",
            description: "I have a flue",
            feedback: "Your flue will be ohk"
          }, {
            date: "dateTime.now()",
            hospital: "Tshilidzini",
            description: "I have a flue",
            feedback: "Your flue will be ohk"
          }]
        },
        {
          name: "Ndou Terrence",
          id: 9909056037087,
          uid: 'safasgsagafas',
          bloodType: "B",
          lastCheckupDate: "date.now()",
          PendingAppointments: 100,
          hospitalVisits: 91,
          medicalHistories: [{
            date: "dateTime.now()",
            hospital: "Tshilidzini",
            description: "I have a flue",
            feedback: "Your flue will be ohk"
          }]
        }, {
          name: "Murendeni Muthambi",
          id: 8309056037087,
          uid: 'safasgs213a',
          bloodType: "O",
          lastCheckupDate: "date.now()",
          PendingAppointments: 0,
          hospitalVisits: 21,
          medicalHistories: [{
            date: "dateTime.now()",
            hospital: "Tshilidzini",
            description: "I have a flue",
            feedback: "Your flue will be ohk"
          }]
        }
      ],
      Hospitals: ['Helen Joseph', 'Tshilidzini']
    }
  },
  methods: {
    saveAllergy() {
      //NewAllergy
      var self = this;
      if (self.NewAllergy.length > 2) {

        if (self.currentUser.allergies) {

          self.currentUser.allergies.push(self.NewAllergy);

        } else {

          self.currentUser.allergies = [self.NewAllergy];
        }
        firebase.database().ref('User/' + self.currentUser.uid).update({
          allergies: self.currentUser.allergies,
        });

        this.updateMedicalHistory = false;
        this.NewAllergy = '';

      }
    },
    saveFeedBack(appointment) {
      var self = this;
      firebase.database().ref('Appointment/' + appointment.id).update({
        status: "solved",
        feedback: self.Feedback
      });
      this.readDueAppointments();
    },
    ApproveAppointment(appointment) {
      // A post entry.
      firebase.database().ref('Appointment/' + appointment.id).update({
        status: "approved",
        doctor_uid: this.currentUser.uid
      });
      this.readAppointmentsPending();
    },
    DeclineAppointment(appointment) {
      // A post entry.
      firebase.database().ref('Appointment/' + appointment.id).update({
        status: "declined",
        doctor_uid: this.currentUser.uid
      });
      this.readAppointmentsPending();
    },
    readDueAppointments() {
      var self = this;
      this.DueAppointments = [];
      firebase.database().ref('/Appointment').once('value').then(function(role) {
        $.map(role.val(), function(value, index) {
          if (value.status == "approved") {
            firebase.database().ref('/User/' + value.patient_uid).once('value').then(function(user) {
              value.patient = user.val();
              value.id = index;
              self.DueAppointments.push(value);
            });
          }
        });
      });
    },
    readAppointmentsPending() {
      var self = this;
      this.PendingAppointments = [];
      firebase.database().ref('/Appointment').once('value').then(function(role) {
        $.map(role.val(), function(value, index) {
          if (value.status == "pending") {
            firebase.database().ref('/User/' + value.patient_uid).once('value').then(function(user) {
              value.patient = user.val();
              value.id = index;
              self.PendingAppointments.push(value);
            });
          }
        });
      });
    }
  },
  mounted() {
    //do something after mounting vue instance
    this.selectedPatient = this.Patients[0];
    var self = this;
    firebase.auth().onAuthStateChanged(
      function(user) {
        if (user) {
          self.readAppointmentsPending();
          self.readDueAppointments();
          self.currentUser = user;
        }
      });
  }

}
</script>
