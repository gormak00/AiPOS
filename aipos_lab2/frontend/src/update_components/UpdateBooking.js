import React, {Component} from "react";
import axios from "axios";
import {Link, withRouter} from "react-router-dom";
import Button from "@material-ui/core/Button";
import {TextField} from "@material-ui/core";
const axiosPOSTconfig = {headers: {'Content-Type': 'application/json'}};


class UpdateBooking extends Component{

    constructor(props) {
        super(props);
        this.state = {
            clientId: "",
            carId: "",
            dateStart: "",
            dateEnd: "",
            rows1: [],
            rows2: []
        };
    }

    onChange = (event) => {
            this.setState({[event.target.id]: event.target.value});
    }

    onSubmit = (event) => {
        let {clientId, carId, dateStart, dateEnd} = this.state;
        event.preventDefault();
        if(clientId === '', carId === '', dateStart === '', dateEnd === ''){
            alert("Enter all Fields");
        }
        else{
            axios.put(`http://localhost:8080/updateBooking/` + this.props.match.params.id, JSON.stringify({
                'id': this.props.match.params.id,
                'clientId': clientId,
                'carId': carId,
                'dateStart': dateStart,
                'dateEnd': dateEnd,
            }), axiosPOSTconfig)
                .then((response) => {
                    alert('Update Completed');
                    this.setState({status: response.data.status});
                })
                .catch((error) => {console.log(error)});
        }

    }

    componentDidMount() {
        axios.get(`http://localhost:8080/booking/`+this.props.match.params.id)
            .then((response) => {this.setState({id: this.props.match.params.id, clientId: response.data.clientId, carId: response.data.carId, dateStart: response.data.dateStart, dateEnd: response.data.dateEnd});})
            .catch((error) => {console.log(error); this.setState({ message: error.message })});

        axios.get('http://localhost:8080/cars')
            .then((response => {this.setState({rows2: response.data});}))
            .catch((error) => {console.log(error); this.setState({ message: error.message })});

        axios.get('http://localhost:8080/clients')
            .then((response) => {this.setState({rows1: response.data});})
            .catch((error) => {console.log(error); this.setState({ message: error.message })});
    }

    render() {
        let {clientId, carId, dateStart, dateEnd} = this.state;
        return(
            <main role="main" className="container">
                <div>
                        <form onSubmit={this.onSubmit}>
                            <TextField id="clientId" type="text" value={clientId} placeholder={"clientId"} onChange={this.onChange}/><br/>
                            <TextField id="carId" type="text" value={carId} placeholder={"carId"} onChange={this.onChange}/><br/>
                            <TextField id="dateStart" type="text" value={dateStart} placeholder={"dateStart"} onChange={this.onChange}/><br/>
                            <TextField id="dateEnd" type="text" value={dateEnd} placeholder={"dateEnd"} onChange={this.onChange}/><br/>

                            <br/><Button onClick={this.onSubmit} variant="contained" color="primary">Update booking</Button><br/>
                            <Button component={Link} to="/bookings" variant="contained" color="primary" >Booking's Table</Button><br/>
                        </form>
                </div>
                <div align="right">
                    {this.state.rows1 === null && <p>Loading menu...</p>}
                    <table className="clients">
                        <thead>
                        <tr>
                            <th width={50}>ID</th>
                            <th width={100}>Name</th>
                            <th width={300}>PhoneNumber</th>
                        </tr>
                        </thead>
                        {this.state.rows1 && this.state.rows1.map(client => (
                            <tbody align="center">
                            <tr>
                                <td width={50}>{client.id}</td>
                                <td width={100}>{client.name}</td>
                                <td width={300}>{client.phoneNumber}</td>
                            </tr>
                            </tbody>
                        ))
                        }
                    </table>
                </div>
                <div align="left">
                    {this.state.rows2 === null && <p>Loading menu...</p>}
                    <br/>
                    <table className="cars">
                        <thead>
                        <tr>
                            <th width={50}>id</th>
                            <th width={50}>number</th>
                            <th width={100}>model</th>
                            <th width={200}>rent company name</th>
                            <th width={50}>rented</th>
                        </tr>
                        </thead>
                        {this.state.rows2 && this.state.rows2.map(car => (
                            <tbody align="center">
                            <tr>
                                <td width={50}>{car.id}</td>
                                <td width={100}>{car.number}</td>
                                <td width={500}>{car.model}</td>
                                <td width={200}>{car.rentCompany.name}</td>
                                <td width={50}>{car.rented}</td>
                            </tr>
                            </tbody>
                        ))}
                    </table>
                </div>
            </main>
        );
    }
}

export default withRouter(UpdateBooking);
