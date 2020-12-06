import Vue from 'vue'
import Vuex from 'vuex'
import axios from "axios"

Vue.use(Vuex)

var apiUrl = "http://192.168.99.100:8089/"

export default new Vuex.Store({
    state: {
        userid: '1',
        user: null,
        events: {},
        eventDetail: {},
        eventDialog: false,
        btnText: "",
        eventId: '',
    },
    getters: {
        events(state) {
            return state.events
        },
        userId(state) {
            return state.userid
        },
        eventDetail(state){
            return state.eventDetail
        },
        eventDialog(state){
            return state.eventDialog
        },
        btnText(state){
            return state.btnText
        },
        eventId(state) {
            return state.eventId
        }
    },
    mutations: {
        updateUserid(state, message) {
            state.userid = message
            console.log(this.userid + 'dsdsff')
        },
        setEvents(state, events) {
            state.events = events;
        },
        updateEventDetail(state, event){
            state.eventDetail = event
        },
        updateEventDialog(state){
            state.eventDialog = !state.eventDialog
        },
        updateBtnText(state, text){
            state.btnText = text
        },
        setEventId(state, id) {
            state.eventId = id;
        }
    },
    actions: {
        getMyEvents(context, id) {
            return axios
                .get( apiUrl + "event/" + id)
                .then((response) => {
                    context.commit("setEvents", response.data);
                })
                .catch(error => {
                    throw new Error(error)
                });
        },
        closeEvent(context,id){
            return axios
                .put( apiUrl + "event/" + id)
                .then((response) => {
                    console.log(response.status)
                })
                .catch((error) => {
                console.log(error.response);
            });
        },
        setEventId(context, id) {
            context.commit("setEventId", id)
        },
        editEvent(context, data) {
            return axios
                .put( apiUrl + "event/", {
                    eventId: this.getters.eventId,
                    eventName: data.eventName,
                    eventDescription: data.eventDescription,
                    dateStart: data.dateStart,
                    dateEnd: data.dateEnd
                })
        },
        deleteEvent(context, id) {
            return axios
                .delete( apiUrl + "event/" + id)
                .then(() => {
                    this.getMyEvents
                })
                .catch(error => {
                    throw new Error(error)
                });
        },
    },
})