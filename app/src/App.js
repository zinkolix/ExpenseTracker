import React from 'react';
import './App.css';
import Category from './Category';
import Home from './Home';
import Expense from './Expense'
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";

function App() {
  return (
    <Router>
      <Switch>
        <Route path = '/' exact={true} component={Home}/>
        <Route path = '/categories' exact={true} component={Category}/>
        <Route path = '/expenses' exact={true} component={Expense}/>

      </Switch>
    </Router>

    );
}

export default App;
