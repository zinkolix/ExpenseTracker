import React, { Component } from "react";
import AppNav from "./AppNav";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import "./App.css";
import "bootstrap/dist/css/bootstrap.css";
import { Table, Container, Form, FormGroup, Button, Label } from "reactstrap";
import { Link } from "react-router-dom";

class Expense extends Component {
  emptyItem = {
    id: 110,
    expensedate: new Date(),
    descript: "New York Business Trip",
    category: {
      id: 1,
      name: "Travel"
    },
    user: {
      id: 1,
      name: "Codeengine11@gmail.com",
      email: "Siamak"
    }
  };

  constructor(props) {
    super(props);
    this.state = {
      // date: new Date(),
      isLoading: true,
      expenses: [],
      categories: [],
      item: this.emptyItem
    };
    this.handleDateChange = this.handleDateChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
    this.handleChange = this.handleChange.bind(this);
  }

  async handleSubmit(event) {
    // event.preventDefault();
    const item = this.state.item;

    await fetch('/api/expenses/', {
      method : 'POST',
      headers : {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      body : JSON.stringify(item),
    });
    
    event.peventDefault();
    console.log(this.props.history.push("/expenses"));
  }

  handleChange(event) {
    const target = event.target;
    const value = target.value;
    const name = target.name;

    let item = { ...this.state.item };
    item[name] = value;
    this.setState({item});
    console.log(item);
  }

  handleDateChange(date) {
    let item = { ...this.state.item };
    item.expensedate = date;
    this.setState({ item });
    console.log(item);
  }

  async remove(id) {
    await fetch("/api/expenses/" + id, {
      method: "DELETE",
      headers: {
        'Accept': "application/json",
        "Content-Type": "application/json"
      }
    }).then(() => {
      let updatedExpenses = [...this.state.expenses].filter(i => i.id !== id);
      this.setState({ expenses: updatedExpenses });
    });
  }

  async componentDidMount() {
    const response = await fetch("/api/categories");
    const body = await response.json();
    this.setState({ categories: body, isLoading: false });

    const responseExp = await fetch("/api/expenses");
    const bodyExp = await responseExp.json();
    this.setState({ expenses: bodyExp, isLoading: false });
  }

  render() {
    const title = <h1>Add Expense</h1>;
    const { categories } = this.state;
    const { expenses, isLoading } = this.state;

    let optionList = categories.map(category => (
      <option value={category.id} key={category.id}>
        {category.name}
      </option>
    ));

    let rows = expenses.map(expense => (
      <tr key={expense.id}>
        <td>{expense.descript}</td>
        <td>{expense.expensedate}</td>
        <td>{expense.category.name}</td>
        <td>
          <Button
            size="sm"
            color="danger"
            onClick={() => this.remove(expense.id)}
          >
            Delete
          </Button>
        </td>
      </tr>
    ));

    if (isLoading) return <div>Loading...</div>;

    return (
      <div>
        <AppNav />
        <Container>
          {title}
          <Form onSubmit={this.handleSubmit}>
            <FormGroup>
              <Label for="title">Title</Label>
              <input
                type="text"
                name="descript"
                id="title"
                onChange={this.handleChange}
              />
            </FormGroup>

            <FormGroup>
              <Label for="title">Category</Label>
              <select>{optionList}</select>
            </FormGroup>
            <FormGroup>
              <Label for="expenseDate">Expense</Label>
              <DatePicker
                selected={this.state.item.expensedate}
                onChange={this.handleDateChange}
              />
            </FormGroup>

            {/* <FormGroup>
              <Label for="location">Expense</Label>
              <input
                type="text"
                name="location"
                id="location"
                onChange={this.handleChange}
              />
            </FormGroup> */}

            <FormGroup>
              <Button color="primary" type="submit">
                Save
              </Button>{" "}
              <Button color="secondary" tag={Link} to="/categories">
                Cancel
              </Button>
            </FormGroup>
          </Form>
        </Container>

        <Container>
          <h3>Expense List</h3>
          <Table className="mt-4">
            <thead>
              <tr>
                <th width="20%">Description</th>
                <th width="30%">Date</th>
                <th width="">Category</th>
                <th width="10%">Action</th>
                <th width=""></th>
              </tr>
            </thead>
            <tbody>{rows}</tbody>
          </Table>
        </Container>
      </div>
    );
  }
}

export default Expense;
