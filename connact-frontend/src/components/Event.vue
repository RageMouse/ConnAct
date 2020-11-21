<template>
  <v-card class="mx-auto" max-width="1500">
    <v-container fluid>
      <v-row dense>
        <v-col
          v-for="card in activeCards"
          :key="card.eventName"
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
              <v-card-title v-text="card.eventName"></v-card-title>
            </v-img>
            <v-card-text>
              <div>
                Date Start:
                {{card.dateStart}}
                <br/>
                Date End:
                {{card.dateEnd}}
              </div>
            </v-card-text>
            <v-card-actions>
            
            <v-dialog
              v-model="dialog"
              width="500"
              :retain-focus="false"

              >
              <template v-slot:activator="{ on, attrs }">
                <v-btn 
                  v-bind="attrs"
                  v-on="on"
                  text
                  @click="openEventDetails(card)"
                  >
                  Details
                </v-btn>
              </template>

              <v-card
                  class="mx-auto"
                  max-width="500"
                >
                  <v-img
                    src="https://cdn.vuetifyjs.com/images/cards/sunshine.jpg"
                    height="200"
                  ></v-img>

                  <v-card-title>
                    {{ eventName }}
                  </v-card-title>

                  <br/>

                  <v-card-subtitle>
                    {{ eventDesc }}
                  </v-card-subtitle>

                  <v-card-text>
                    {{ eventStart }}
                  </v-card-text>

                  <v-card-text>
                    {{ eventEnd }}
                  </v-card-text>

                  <v-btn
                    color="orange lighten-2"
                    text
                    @click="dialog=false"
                  >
                    Join event
                  </v-btn>

                </v-card>

            </v-dialog>

              <v-spacer></v-spacer>

              <v-btn icon>
                <v-icon>mdi-heart</v-icon>
              </v-btn>
            </v-card-actions>
          </v-card>
        </v-col>
      </v-row>
    </v-container>
  </v-card>
</template>

<script>
export default {
  name: "#app",
  mounted() {
    this.loadEvents();
  },
  methods: {
    loadEvents: function () {
      this.axios
        .get("http://192.168.178.20:8089/event/")
        .then((response) => (this.cards = response.data));
    },
    passEvent: function(eventId){
      this.selectedEvent = eventId
    },
    openEventDetails: function(event){
      this.eventName = event.eventName
      this.eventDesc = event.description
      this.eventStart = event.dateStart
      this.eventEnd = event.dateEnd
    },
  },
  data: () => ({
    cards: [],
    dialog: false,
    eventName: "",
    eventDesc: "",
    eventStart: 0,
    eventEnd: 0,
  }),
  computed: {
    activeCards: function(){
      return this.cards.filter(c => c.active==true)
    },
  }
};
</script>