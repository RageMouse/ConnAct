<template>
  <v-card class="mx-auto" max-width="1500">
    <v-container fluid>
      <v-row dense>
        <v-col
          v-for="event in events"
          :key="event.eventId"
          cols="12"
          md="4"
          sm="3"
          xs="12"
        >
          <v-card>
            <v-img
              :src="'http://placeimg.com/320/180/animals'"
              class="white--text align-end"
              gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)"
              height="200px"
            >
              <v-card-title v-text="event.eventName"></v-card-title>
            </v-img>
            <v-card-text>
              <div>
                Date Start:
                {{event.dateStart}}
                <br/>
                Date End:
                {{event.dateEnd}}
              </div>
            </v-card-text>
            <v-card-actions>
              <!-- <router-link :to="{path: '/showEvent/' + card.eventId}" tag="v-btn"> -->
              <v-btn text>Details</v-btn>
              <!-- </router-link> -->

              <v-spacer></v-spacer>

              <v-dialog
                  v-model="dialog"
                  persistent
                  max-width="500"
                >
                  <template v-slot:activator="{ on, attrs }">
                    <v-btn
                      color="primary"
                      dark
                      v-bind="attrs"
                      v-on="on"
                    >
                      Close event
                    </v-btn>
                  </template>
                  <v-card>
                    <v-card-title class="headline">
                      Are you sure you want to close this event?
                    </v-card-title>
                    <v-card-text>This action cannot be undone.</v-card-text>
                    <v-card-actions>
                      <v-spacer></v-spacer>
                      <v-btn
                        text
                        @click="dialog = false"
                      >
                        Cancel
                      </v-btn>
                      <v-btn
                        color="primary"
                        text
                        @click="closeEvent(event.eventId)"
                      >
                        Confirm
                      </v-btn>
                    </v-card-actions>
                  </v-card>
              </v-dialog>
            </v-card-actions>
          </v-card>
        </v-col>
      </v-row>
    </v-container>
    <v-alert 
        style=""
        type="success"
        :value="alert"
        max-width="1500"
      >
        Event has been closed.
    </v-alert>
  </v-card>
</template>

<script>
export default {
  name: "myEvents",
  computed: {
    events() {
      return this.$store.getters.events;
    },
  },
  mounted() {
    this.loadEvents();
    console.log(this.$store.getters.userId)
    this.dialog = false
    this.hideAlert()
  },
  methods: {
    loadEvents() {
      return this.$store.dispatch("getMyEvents", this.$store.getters.userId);
    },
    closeEvent(eventId){
      this.axios
        .put("http://192.168.178.20:8089/event/"+ eventId)
        .then((response) => {
          console.log(response.status)
          if (response.status !== 204) {
            this.alertSucces = true;
          }
            this.alert = true
            this.dialog = false
        })
        .catch((error) => {
          console.log(error.response);
      });
    },
    alertTimer: function () {
      window.setInterval(() => {
        this.alert = false;
      }, 3000)    
    },
    hideAlert: function(){
      if(alert){
        this.alertTimer();
      }
    },
  },
  data: () => ({
    cards: [],
    dialog: false,
    eventId: 0,
    alert: false,
  }),
};
</script>