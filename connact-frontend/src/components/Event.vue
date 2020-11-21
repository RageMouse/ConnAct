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
              <!-- <router-link :to="{path: '/showEvent/' + card.eventId}" tag="v-btn"> -->
              <!-- </router-link> -->

            <v-dialog
              v-model="dialog"
              width="500"
              >
              <template v-slot:activator="{ on, attrs }">
                <v-btn 
                  v-bind="attrs"
                  v-on="on"
                  text
                  >
                  Details
                </v-btn>
              </template>
                
              <v-card>
                <v-card-title class="headline grey lighten-2">
                  {{ card.eventName }}
                </v-card-title>
        
                <v-card-text>
                  Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
                </v-card-text>
        
                <v-divider></v-divider>
        
                <v-card-actions>
                  <v-spacer></v-spacer>
                  <v-btn
                    color="primary"
                    text
                    @click="dialog = false"
                  >
                    Join
                  </v-btn>
                </v-card-actions>
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
  },
  data: () => ({
    cards: [],
    dialog: false,

  }),
  computed: {
    activeCards: function(){
      return this.cards.filter(c => c.active==true)
    },
  }
};
</script>