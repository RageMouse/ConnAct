<template>
  <v-card class="mx-auto" max-width="1500">
    <v-container fluid>
      <v-row dense>
        <v-col
          v-for="card in cards"
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
              <!-- <router-link :to="{path: '/showEvent/' + card.eventId}" tag="v-btn"> -->
              <v-btn text>Details</v-btn>
              <!-- </router-link> -->

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
  },
  data: () => ({
    cards: [],
  }),
};
</script>