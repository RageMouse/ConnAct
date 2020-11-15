import Vue from 'vue'
import Vuex from 'vuex'
import axios from "axios"

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        userid: '1',
        user: null,
        events: {}
    },
    getters: {
        events(state){
            return state.events
        },
        userId(state){
            return state.userid
        }
    },
    mutations: {
        updateUserid (state, message) {
            state.userid = message
            console.log(this.userid+'dsdsff')
        },
        setEvents(state, events){
            state.events = events;
        },
    },
    actions: {
        getMyEvents(context, id) {
            return axios
                .get("http://192.168.99.100:8089/event/" + id)
                .then((response) => {
                    context.commit("setEvents", response.data);
                })
                .catch(error => {
                    throw new Error(error)
                });
        }
    },
})