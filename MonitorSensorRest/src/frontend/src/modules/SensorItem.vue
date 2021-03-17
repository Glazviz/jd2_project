<template>
  <tr v-if="value && sensorFields && Object.values(value).length" class="component sensor-item">
    <td class="sensor-item__field" v-for="(f, i) in sensorFields" v-bind:key="('table-body-field-' + i)">
      <span v-if="f.field != 'actions'">{{value[f.field]}}</span>
      <span v-else class="sensor-item__field__actions">
        <slot></slot>
      </span>
    </td>
  </tr>
</template>

<script>
export default {
  name: 'SensorItem',
  props: {
    sensorFields: {
      type: Array,
      default: () => [],
      required: true
    },
    value: {
      type: Object,
      default: () => {
        return {}
      },
      required: true
    }
  },
  data () {
    return {}
  }
}
</script>

<style scoped>
</style>
