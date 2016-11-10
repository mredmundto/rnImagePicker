/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 * @flow
 */

import React, { Component } from 'react';
import {
  AppRegistry,
  StyleSheet,
  Text,
  View, 
  NativeModules,
  TouchableOpacity
} from 'react-native';

export default class rntemplate extends Component {

  

  render() {
    return (
      <View style={styles.container}>
        <Text style={styles.welcome}>
          testing changes 
        </Text>
        <Text style={styles.instructions}>
          To get started, edit index.android.js
        </Text>
        <Text style={styles.instructions}>
          Double tap R on your keyboard to reload,{'\n'}
          Shake or press menu button for dev menu
        </Text>
        <TouchableOpacity onPress={() => {
          console.log("NATIVE MDULES");
          console.log(NativeModules.ImagePicker);
          console.log(NativeModules);
          NativeModules.ImagePicker.openSelectDialog(
            {}, // no config yet 
            (uri) => { console.log(uri) }, 
            (error) => { console.log(error) }
          )
        }}>
          <Text>{'Test Image Picker'}</Text>
        </TouchableOpacity>
      </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#F5FCFF',
  },
  welcome: {
    fontSize: 20,
    textAlign: 'center',
    margin: 10,
  },
  instructions: {
    textAlign: 'center',
    color: '#333333',
    marginBottom: 5,
  },
});

AppRegistry.registerComponent('rntemplate', () => rntemplate);
