<template>
  <v-card class="mx-auto" max-width="1500">
    <v-container fluid>
      <v-row dense>
        <v-col
          v-for="card in activeCards"
          :key="card.eventId"
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

            <v-btn
              id="detailsButton" 
              text
              @click="openEventDetails(card)"
              >
              Details
            </v-btn>

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
  name: "allEvents",
  mounted() {
    this.loadEvents();
    this.$store.commit('updateBtnText', "join")
  },
  methods: {
    loadEvents: function () {
      this.axios
        .get("http://192.168.99.100:8089/event/")
        .then((response) => (this.cards = response.data));
        console.log(this.cards)
    },
    openEventDetails: function(eventDetails){
      this.$store.commit('updateEventDetail', eventDetails)
      this.$store.commit('updateEventDialog')
    }
  },
  data: () => ({
    cards: [],
  }),
  computed: {
    activeCards: function(){  
      return this.cards.filter(c => c.active==true)
    },
  }
};
</script>