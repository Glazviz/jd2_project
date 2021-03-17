<template>
  <div class="page sensor">
    <div class="form-field" v-for="(f, i) in sensorsFields" v-bind:key="('sensor-input-' + i)">

      <Select
        v-if="f.type === 'select'"
        v-bind:id="'sensor-' + f.field"
        v-bind:name="'sensor-' + f.field"
        v-bind:label="f.field"
        v-bind:type="f.type"
        v-model="sensor[f.field]"
        v-bind:required="f.required"
        v-validate="f.validation" data-vv-as=" "
        v-bind:error="errors.first('sensor-' + f.field)"
      >
        <option
          v-for="(v, j) in f.values"
          v-bind:value="v.id"
          v-bind:key="('sensor-input-' + i + '-val-' + j)"
        >{{v.name}}</option>
      </Select>
      <Input
        v-else
        v-bind:id="'sensor-' + f.field"
        v-bind:name="'sensor-' + f.field"
        v-bind:label="f.field"
        v-bind:type="f.type"
        v-model="sensor[f.field]"
        v-bind:required="f.required"
        v-validate="f.validation" data-vv-as=" "
        v-bind:error="errors.first('sensor-' + f.field)"
      />
    </div>

    <button v-on:click="back">Back</button>
    <button v-on:click="save" v-bind:disabled="!!(errors && errors.items && Object.values(errors.items).length)">Save</button>
    <pre>{{sensor}}</pre>
  </div>
</template>

<script>
import Input from '../modules/Input'
import Select from '../modules/Select'
export default {
  name: 'Sensor',
  components: {Input, Select},
  data () {
    return {
      sensorsFields: [
        {
          field: 'sensorsName',
          label: 'Name',
          type: 'text',
          required: true,
          validation: {required: true, min: 3, max: 255},
          sort: 1
        },
        {
          field: 'sensorsType',
          label: 'Sensor type',
          type: 'select',
          required: false,
          validation: {required: false, integer: true},
          sort: 2,
          values: []
        },
        {
          field: 'sensorsUnit',
          label: 'Sensor unit',
          type: 'select',
          required: false,
          validation: {required: false, integer: true},
          sort: 3,
          values: []
        },
        {
          field: 'sensorsModel',
          label: 'Model',
          type: 'text',
          required: true,
          validation: {required: true, min: 3, max: 255},
          sort: 4
        },
        {
          field: 'sensorsRangeFrom',
          label: 'Range From',
          type: 'number',
          required: true,
          validation: {required: true, min_value: 0, max_value: 999, integer: true},
          sort: 5
        },
        {
          field: 'sensorsRangeTo',
          label: 'Range To',
          type: 'number',
          required: true,
          validation: {required: true, min_value: 0, max_value: 999, integer: true},
          sort: 6
        },
        {
          field: 'sensorsLocation',
          label: 'Location',
          type: 'text',
          required: true,
          validation: {required: true, min: 3, max: 255},
          sort: 7
        },
        {
          field: 'descriptions',
          label: 'Description',
          type: 'textarea',
          required: false,
          validation: {required: false, min: 0, max: 500},
          sort: 8
        }
      ],
      sensor: {
        id: null,
        sensorsName: null,
        sensorsModel: null,
        sensorsType: null,
        sensorsRangeFrom: null,
        sensorsRangeTo: null,
        sensorsUnit: null,
        sensorsLocation: null,
        descriptions: null
      },
      sensorDefault: {
        id: null,
        sensorsName: null,
        sensorsModel: null,
        sensorsType: null,
        sensorsRangeFrom: null,
        sensorsRangeTo: null,
        sensorsUnit: null,
        sensorsLocation: null,
        descriptions: null
      }
    }
  },
  computed: {
    sensorId () {
      return (parseInt(this.$route.params.id) > 0) ? this.$route.params.id : null
    }
  },
  methods: {
    init () {
      this.$nextTick(() => {
        if (this.sensorId) this.getSensor(this.sensorId)
        else this.sensor = Object.assign({}, this.sensorDefault)

        this.getSensorTypes().then(types => {
          let field = this.sensorsFields.find(item => item.field === 'sensorsType')
          field.values = Object.assign([], types)
          this.sortFields()
        })
        this.getSensorUnits().then(units => {
          let field = this.sensorsFields.find(item => item.field === 'sensorsUnit')
          field.values = Object.assign([], units)
          this.sortFields()
        })
      })
    },
    getSensor (id) {
      return this.$http.get(this.$root.env.endpoint + '/sensors/' + id).then(r => {
        if (r && r.body) {
          this.sensor = Object.assign({}, r.body)
          this.sensor.sensorsType = this.sensor.sensorType.id
          this.sensor.sensorsUnit = this.sensor.sensorUnit.id
        }
        return this.sensor
      }).catch(e => console.log(e))
    },
    getSensorTypes () {
      return this.$http.get(this.$root.env.endpoint + '/type').then(r => {
        if (r && r.body) return r.body
        return []
      }).catch(e => [])
    },
    getSensorUnits () {
      return this.$http.get(this.$root.env.endpoint + '/unit').then(r => {
        if (r && r.body) return r.body
        return []
      }).catch(e => [])
    },
    sortFields () {
      this.sensorsFields.sort((a, b) => a.sort - b.sort)
    },
    back () {
      this.$router.push({name: 'Home'})
    },
    save () {
      return this.$validator.validateAll().then(valid => {
        if (!valid) return
        if (this.sensor.id) return this.putSensor()
        else return this.postSensor()
      })
    },
    postSensor () {
      return this.$http.post(this.$root.env.endpoint + '/sensors', this.sensor).then(r => {
        if (r && r.body) this.$router.push({name: 'Sensor', params: {id: r.body.id}})
      }).catch(e => console.log(e))
    },
    putSensor () {
      if (this.sensor.sensorsUnit) this.sensor.sensorUnit = {id: this.sensor.sensorsUnit}
      if (this.sensor.sensorsType) this.sensor.sensorType = {id: this.sensor.sensorsType}

      return this.$http.put(this.$root.env.endpoint + '/sensors/' + this.sensor.id, this.sensor).then(r => {
        if (r && r.body) this.init()
      }).catch(e => console.log(e))
    }
  },
  created () {
    this.init()
  }
}
</script>

<style scoped>
</style>
