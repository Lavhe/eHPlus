<template>
<div>
  <h1>
    <center>
        eHPlus
        <div class="logo" style="background-color:transparent !important">
          <img class="avatar" src="static/assets/logo.png" style="background-color:transparent !important">
        </div>
        eHPlus
    </center>
  </h1>

<div class="content">
    <div id="project-dashboard" class="page-layout simple">

      <div class="page-content-wrapper">

        <div class="page-content">

        <div class="widget-group row">
          <!-- WIDGET 7 -->
          <div class="col-12 col-lg-6 p-3">

            <div class="widget widget-7 card">

              <div class="widget-header px-4 row no-gutters align-items-center justify-content-between">

                <div class="col">
                  <br />
                  <center><span class="h4">Nearby Hospitals</span></center>
                </div>
              </div>

              <div class="widget-content p-4">

                <div class="py-4 row no-gutters align-items-center justify-content-between">

                  <div class="col">

                    <div class="h6">Bertha Gxowa Hospital</div>

                    <div>
                      <span class="text-muted">1 hour 30 minutes away</span>

                    </div>

                  </div>

                  <div class="col-auto">
                    <button type="button" class="btn btn-icon">
                                   <i class="icon icon-dots-vertical"></i>
                               </button>
                  </div>
                </div>

                <div class="py-4 row no-gutters align-items-center justify-content-between">

                  <div class="col">

                    <div class="h6">Dunvegan Medical Centre</div>

                    <div>
                      <span class="text-muted">1 hour away</span>

                    </div>

                  </div>

                  <div class="col-auto">
                    <button type="button" class="btn btn-icon">
                                   <i class="icon icon-dots-vertical"></i>
                               </button>
                  </div>
                </div>

                <div class="py-4 row no-gutters align-items-center justify-content-between">

                  <div class="col">

                    <div class="h6">Life Roseacres Hospital</div>

                    <div>
                      <span class="text-muted">45 minutes away</span>

                    </div>

                  </div>

                  <div class="col-auto">
                    <button type="button" class="btn btn-icon">
                                   <i class="icon icon-dots-vertical"></i>
                               </button>
                  </div>
                </div>
              </div>
            </div>
          </div>

          <!-- WIDGET 6 -->
          <div class="col-12 col-lg-6 p-3">

            <div class="widget widget6 card">

              <div class="widget-header px-4 row no-gutters align-items-center justify-content-between">

                <div class="col">
                  <br />
                  <center><span class="h4">Doctors stats</span></center>
                </div>
              </div>

              <div class="widget-content">

                <div class="row no-gutters">

                  <div class="col-12">

                    <div id="widget6-main-chart" class="p-4">
                      <svg></svg>
                    </div>

                  </div>

                  <div class="divider col-12"></div>

                  <div id="added-tasks" class="col-6 d-flex flex-column align-items-center justify-content-center py-4">

                    <div class="count h2">5</div>

                    <div class="count-title">Total Doctors</div>

                  </div>

                  <div id="completed-tasks" class="col-6 d-flex flex-column align-items-center justify-content-center py-4">

                    <div class="count h2">10</div>

                    <div class="count-title">Total Ambulances</div>

                  </div>

                </div>
              </div>
            </div>
          </div>
          <div class="col-12 col-lg-12 p-3">
            <div class="widget widget5 card">

              <div class="widget-header px-4 row no-gutters align-items-center justify-content-between">

                <div class="col">
                  <br />
                  <span class="h4">Hospital Progress</span>
                </div>
              </div>

              <div class="widget-content p-4">

                <div class="row">

                  <div class="col-12 col-lg-6">

                    <div id="widget5-main-chart" style="height:100%" >
                      <svg></svg>
                    </div>

                  </div>

                  <div class="col-12 col-lg-6">

                    <div id="widget5-supporting-charts" class="row">
                      <div class="col-12">
                        <h4>Annual Death Statistics</h4>
                      </div>
                      <div class="col-6 p-4">

                        <div id="widget5-created-chart">
                          <div class="h6 text-muted">Accident</div>
                          <div class="h2 count">48</div>
                          <div class="chart-wrapper">
                            <svg></svg>
                          </div>
                        </div>

                      </div>

                      <div class="col-6 p-4">

                        <div id="widget5-closed-chart">
                          <div class="h6 text-muted">Diabetes</div>
                          <div class="h2 count">10</div>
                          <div class="chart-wrapper">
                            <svg></svg>
                          </div>
                        </div>

                      </div>

                      <div class="col-6 p-4">

                        <div id="widget5-re-opened-chart">
                          <div class="h6 text-muted">HIV / AIDS</div>
                          <div class="h2 count">20</div>
                          <div class="chart-wrapper">
                            <svg></svg>
                          </div>
                        </div>

                      </div>

                      <div class="col-6 p-4">

                        <div id="widget5-wont-fix-chart">
                          <div class="h6 text-muted">Breast Cancer</div>
                          <div class="h2 count">4</div>
                          <div class="chart-wrapper">
                            <svg></svg>
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
</div>

</div>
</template>
<script type="text/javascript" src="static/assets/js/apps/dashboard/project.js"></script>
<script>
  export default {
    name: 'Home',
    data() {
      return {
        msg: 'Welcome to Your Vue.js App'
      }
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1,
h2 {
  font-weight: normal;
}

ul {
  list-style-type: none;
  padding: 0;
}

li {
  display: inline-block;
  margin: 0 10px;
}

a {
  color: #42b983;
}
</style>
