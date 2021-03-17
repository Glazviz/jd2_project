<template>
  <div v-if="sensorFields && sensorFields.length" class="component sensors-list">
    <header class="sensors-list__header">
      <slot slot-scope="header"></slot>
    </header>
    <div class="sensors-list__body">
      <table class="sensors-list__table">
        <thead class="sensors-list__table__head">
            <th v-for="(field, i) in sensorFields" v-bind:key="('table-head-field-' + i)">{{field.label}}</th>
        </thead>
        <tbody>
          <slot></slot>
        </tbody>
      </table>
    </div>
    <footer class="sensors-list__footer">
      <slot slot-scope="footer"></slot>
    </footer>
  </div>
</template>

<script>
export default {
  name: 'SensorsList',
  props: {
    sensorFields: {
      type: Array,
      default: () => [],
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
