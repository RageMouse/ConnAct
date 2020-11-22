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
                {{ event.dateStart }}
                <br />
                Date End:
                {{ event.dateEnd }}
              </div>
            </v-card-text>
            <v-card-actions>
              <!-- <router-link :to="{path: '/showEvent/' + card.eventId}" tag=v"-btn"> -->
              <v-btn 
                text
                @click="openEventDetails(event)"
                >
                Details
              </v-btn>

              <!-- </router-link> -->
              <v-spacer></v-spacer>
              <v-btn color="primary" @click="setEventId(event.eventId)" @click.stop="showEditForm=true">Edit</v-btn> 
            </v-card-actions>
          </v-card>
        </v-col>
      </v-row>
    </v-container>
      <EditEvent :visible="showEditForm" @close="showEditForm=false"/>
  </v-card>
</template>

<script>
import EditEvent from '@/components/EditEvent.vue'
 
export default {
  name: "myEvents",
  components: {
    EditEvent
  },
  computed: {
    events() {
      return this.$store.getters.events;
    },
  },
  mounted() {
    this.loadEvents();
    this.$store.commit('updateBtnText', "close")
  },
  methods: {
    loadEvents() {
      return this.$store.dispatch("getMyEvents", this.$store.getters.userId);
    },
    openEventDetails(eventDetails){
      this.$store.commit('updateEventDetail', eventDetails)
      this.$store.commit('updateEventDialog')
    },
    setEventId(id) {
      return this.$store.dispatch("setEventId", id);
    },
  },
  data: () => ({
    cards: [],
    expand: false,
    showEditForm: false,
  }),
};
</script>
