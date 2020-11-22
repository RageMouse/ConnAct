<template>
  <v-container>
    <v-row class="text-center">
      <v-col class="mb-4">
        <h1 class="display-2 font-weight-bold mb-3">My Events Page</h1>
        <CreateEvent
        v-if="showCreateEvent"
        />
        <MyEvents/>
        <EventDetails/>
        <v-btn id="createEvent" class="mr-2" depressed small color="primary" @click="toggleCreateEvent">Create Event</v-btn>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
// @ is an alias to /src
import CreateEvent from '@/components/CreateEvent.vue'
import MyEvents from '@/components/MyEvents.vue'
import EventDetails from '@/components/EventDetails.vue'

export default {
  name: 'Home',
  components: {
    CreateEvent,
    MyEvents,
    EventDetails
  },
   data: () => ({
    showCreateEvent: false
  }),
  methods: {
    toggleCreateEvent () {
      this.showCreateEvent = !this.showCreateEvent;
    }
  }
}
</script>