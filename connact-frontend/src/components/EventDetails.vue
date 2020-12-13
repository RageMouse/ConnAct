<template>
  <v-dialog
    width="500"
    :retain-focus="false"
    v-model="getDialog"
    @click:outside="closeDialog"
  >
    <v-card class="mx-auto" max-width="500">
      <v-img
        src="https://cdn.vuetifyjs.com/images/cards/sunshine.jpg"
        height="200"
      ></v-img>

      <v-card-title>
        {{ getEvent.eventName }}
      </v-card-title>

      <br />

      <v-card-subtitle v-if="!getEvent.active">
        event active: {{ getEvent.active }}
      </v-card-subtitle>

      <v-card-subtitle>
        {{ getEvent.description }}
      </v-card-subtitle>

      <v-card-text>
        {{ getEvent.dateEnd }}
      </v-card-text>

      <v-card-text>
        {{ getEvent.dateStart }}
      </v-card-text>

      <v-btn v-if="getBtnText=='join'"
              color="primary"
              text
              @click="join(getEvent.eventId)"
              >
              Join event
            </v-btn>
            <div v-if="userid==getEvent.ownerId">
             <ul id="requests">
                 <li v-for="request in requests" :key="request.id">
                   <v-btn
                    color="orange lighten-2"
                    text
                    @click.native.prevent="acceptRequest(request.id)"
                  >
                    Accept
                  </v-btn>
                  <v-btn
                    color="orange lighten-2"
                    text
                    @click.native.prevent="join(eventId)"
                  >
                    Decline
                  </v-btn>
                 {{ request.employee.userName }}
                 </li>
              </ul>
             </div>
            <div>
             <ul id="users">
                 <li v-for="user in users" :key="user.event.eventId">
                 <v-card-text>
                   <!-- In de lijst van request wordt de request verwijdert met user.id ook wel requestid -->
                   <v-btn
                    color="red"
                    text
                    @click.native.prevent="kick(user.id)"
                  >
                    X
                  </v-btn>
                    {{ user.employee.userName }}
                  </v-card-text>
                 </li>
              </ul>
             </div>

                  
              <v-btn
                color="red"
                text
                @click="leave"
                v-if="$route.path=='/event'"
              >
                Leave event
              </v-btn>

             <div v-if="userid==getEvent.ownerId">
               <v-card-title>
                Invite employees
                </v-card-title>
                <v-text-field
                v-model="username"
                label="Username employee"
                outlined
                ></v-text-field>
                <v-btn
                    color="primary"
                    text
                    @click.native.prevent="searchEmployee(username,getEvent.eventId)"
                  >
                    Invite employee
                  </v-btn>
                
             </div>
            <v-alert
             class="elevation-12 mx-auto"
             outlined
             type="success"
             text
             :value="alertSucces"
             max-width="1000px"
            >
              Request sent
             </v-alert>

      <v-btn
        v-if="getBtnText == 'close'"
        color="primary"
        text
        @click="closeEvent"
      >
        Close event
      </v-btn>

      <v-alert
        class="elevation-12 mx-auto"
        outlined
        type="success"
        text
        :value="alert"
      >
        Event has been closed
      </v-alert>
    </v-card>
  </v-dialog>
</template>

<script>
export default {
  mounted() {
    this.hideAlert();
  },
  methods: {
     acceptRequest: function (id) {
       this.$store.dispatch('acceptRequest', id)
    },
    searchEmployee(username,eventid) {
            this.axios
          .post("http://192.168.178.20:8089/employee/searchEmployee", {  
            userName: username
          })
          .then((response) => {
            this.employeeid = response.data.employeeId
            this.$store.dispatch("createInvite", {
              employeeid: this.employeeid,eventid
            });

            if (response.status !== 204) {
              this.alertSucces = true;
            }
          })
          .catch((error) => {
            console.log(error.response);
          });
        },
    kick(requestid){
         this.$store.dispatch('removeRequest', requestid)
    },
    invite(eventid){
      this.$store.dispatch('inviteEmployee',eventid)
    },
    join(eventid) {
      
     this.axios
        .post("http://192.168.178.20:8089/event/request", {
          employeeId: this.$store.state.userid,
          eventId: eventid,  
          requesttype: "request",
          accepted: false
        })
        .then((response) => {
         
          if (response.status !== 204) {
            this.alertSucces = true;
          }
        })
        .catch((error) => {
          console.log(error.response);
        });
     },
    closeDialog: function () {
      this.$store.commit("updateEventDialog");
    },
    closeEvent: function () {
      this.$store.dispatch("closeEvent", this.getEvent.eventId);
    },
    alertTimer: function () {
      window.setInterval(() => {
        this.alert = false;
      }, 3000);
    },
    hideAlert: function () {
      if (alert) {
        this.alertTimer();
      }
    },
    leave: function () {
      this.$store.dispatch('leaveEvent', this.userid)
    }
  },
  data: () => ({
    alert: false,
    alertSucces:false,
    username: '',
    employeeid:null,
  }),
  computed: {
    getDialog: {
      get() {
        return this.$store.getters.eventDialog;
      },
      set(bool) {
        return bool;
      },
    },
    getEvent: function () {
      return this.$store.getters.eventDetail;
    },
    getBtnText: function () {
      return this.$store.getters.btnText;
    },
    requests() {
      return this.$store.getters.requests;
    },
    users(){
      return this.$store.getters.users
    },
    userid: {
        get () {
        return this.$store.state.userid
          }
        },
  },
};
</script>