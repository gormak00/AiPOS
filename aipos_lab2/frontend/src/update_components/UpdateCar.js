import React, {Component} from "react";
import axios from "axios";
import {Link, withRouter} from "react-router-dom";
import Button from "@material-ui/core/Button";
import {TextField} from "@material-ui/core";
const axiosPOSTconfig = {headers: {'Content-Type': 'application/json'}};


class UpdateCar extends Component{

    constructor(props) {
        super(props);
        this.state = {
            number: '',
            model: '',
            nameOfRentCompany: '',
            rented: '',
            rows1: []
        };
    }

    onChange = (event) => {
        this.setState({[event.target.id]: event.target.value});
    }

    onSubmit = (event) => {
        let {number, model, nameOfRentCompany, rented} = this.state;
        event.preventDefault();
        if((number === '') || (model === '') || (nameOfRentCompany === '') || (rented === '')){
            alert('Enter all Fields');
        }
        else{
            axios.put(`http://localhost:8080/updateCar/` + this.props.match.params.id, JSON.stringify({
                'id': this.props.match.params.id,
                'number': number,
                'model': model,
                'nameOfRentCompany': nameOfRentCompany,
                'rented': rented,
            }), axiosPOSTconfig)
                .then((response) => {
                    alert('Update Completed');
                    this.setState({status: response.data.status});
                })
                .catch((error) => {console.log(error)});
        }
    }

    componentDidMount() {
        console.log(this.props);
        axios.get(`http://localhost:8080/car/`+this.props.match.params.id)
            .then((response) => {this.setState({id: this.props.match.params.id, number: response.data.id, model: response.data.model, nameOfRentCompany: response.data.nameOfRentCompany, rented: response.data.rented});})
            .catch((error) => {console.log(error); this.setState({ message: error.message })});

        axios.get('http://localhost:8080/rentCompanies')
            .then((response => {this.setState({rows1: response.data});}))
            .catch((error) => {console.log(error); this.setState({ message: error.message })});
    }

    render() {
        let {number, model, nameOfRentCompany, rented} = this.state;
        return(
            <main role="main" className="container">
                <div>
                    <form onSubmit={this.onSubmit}>
                        <TextField id="number" type="text" value={number} placeholder={"number"} onChange={this.onChange}/><br/>
                        <TextField id="model" type="text" value={model} placeholder={"model"} onChange={this.onChange}/><br/>
                        <TextField id="rentCompany.name" type="text" value={nameOfRentCompany} placeholder={"rentCompany.name"} onChange={this.onChange}/><br/>
                        <TextField id="rented" type="text" value={rented} placeholder={"rented"} onChange={this.onChange}/><br/>

                        <br/><Button onClick={this.onSubmit} variant="contained" color="primary">Update car</Button><br/>
                        <br/><Button component={Link} to="/cars" variant="contained" color="primary">Car's Table</Button>
                    </form>
                </div>

                <div align="right">
                    {this.state.rows1 === null && <p>Loading menu...</p>}
                    <table className="rentCompanies">
                    <thead>
                    <tr>
                    <th width={50}>ID</th>
                    <th width={100}>Name</th>
                    </tr>
                    </thead>
                {this.state.rows1 && this.state.rows1.map(rentCompany => (
                    <tbody align="center">
                    <tr>
                    <td width={50}>{rentCompany.id}</td>
                    <td width={100}>{rentCompany.name}</td>
                    </tr>
                    </tbody>
                ))
                }
                    </table>
                </div>
            </main>
        );
    }
}

export default withRouter(UpdateCar);
