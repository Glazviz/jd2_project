<template>
  <div class="page home">
    <SensorsList v-bind:sensor-fields="sensorsFields">
      <SensorItem
        v-for="(sensor, i) in sensors"
        v-bind:key="('sensor-' + i + '-' + sensors.id)"
        v-bind:sensor-fields="sensorsFields"
        v-bind:value="sensor"
      >
        <router-link v-bind:to="{name: 'Sensor', params: {id: sensor.id}}">Edit</router-link>
        <button v-on:click="deleteSensor(sensor)">Delete</button>
      </SensorItem>
      <template name="header">
        <Input name="sensors-search" v-model="searchString" />
      </template>
      <template name="footer">
        <paginate
          v-bind:page-count="totalPages"
          v-bind:initial-page="page"
          v-bind:force-page="page"
          prev-text="Prev"
          next-text="Next"
          v-bind:click-handler="handlePageChange"
        ></paginate>
        <router-link v-bind:to="{name: 'Sensor', params: {id: 0}}">Create</router-link>
      </template>
    </SensorsList>
  </div>
</template>

<script>
import SensorsList from '../modules/SensorsList'
import SensorItem from '../modules/SensorItem'
import Input from '../modules/Input'
export default {
  name: 'Home',
  components: {SensorsList, SensorItem, Input},
  data () {
    return {
      sensorsFields: [
        {
          field: 'id',
          label: 'ID'
        },
        {
          field: 'sensorsName',
          label: 'Name'
        },
        {
          field: 'sensorsModel',
          label: 'Model'
        },
        {
          field: 'sensorsType',
          label: 'Type'
        },
        {
          field: 'sensorsRangeFrom',
          label: 'Range From'
        },
        {
          field: 'sensorsRangeTo',
          label: 'Range To'
        },
        {
          field: 'sensorsUnit',
          label: 'Unit'
        },
        {
          field: 'sensorsLocation',
          label: 'Location'
        },
        {
          field: 'descriptions',
          label: 'Description'
        },
        {
          field: 'actions',
          label: 'Actions'
        }
      ],
      sensors: [],
      searchString: null,
      totalPages: 0,
      page: 1,
      count: 5
    }
  },
  watch: {
    'searchString' (value, oldValue) {
      if (!value || !value.length || value.length >= 3) {
        this.page = 1
        this.getSensors()
      }
    }
  },
  methods: {
    init () {
      this.$nextTick(() => this.getSensors())
    },
    getSensors () {
      this.$http.get(this.$root.env.endpoint + '/sensors', {
        params: {
          search: this.searchString,
          page: (this.page - 1),
          pageSize: this.count
        }
      }).then(r => {
        if (r && r.body) {
          this.sensors = Object.assign([], r.body.sensors.map(sensor => {
            sensor.sensorsType = sensor.sensorType.name
            sensor.sensorsUnit = sensor.sensorUnit.name
            return sensor
          }))
          this.totalPages = r.body.totalPages
        }
      }).catch(e => console.log(e))
    },
    deleteSensor (sensor) {
      if (!sensor || !confirm('Are you sure to delete sensor "' + sensor.sensorsName + '"?')) return
      return this.$http.delete(this.$root.env.endpoint + '/sensors/' + sensor.id).then(r => {
        this.getSensors()
      }).catch(e => console.log(e))
    },
    handlePageChange (value) {
      this.page = value
      this.getSensors()
    }
  },
  created () {
    this.init()
  }
}
</script>

<style scoped>
</style>
