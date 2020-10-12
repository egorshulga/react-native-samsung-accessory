import * as React from 'react'
import { StyleSheet, View, Text } from 'react-native'

export default function App(): React.ReactElement {
  React.useEffect(() => {
  }, [])

  return (
    <View style={styles.container}>
      <Text>Result: {0}</Text>
    </View>
  )
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
})
