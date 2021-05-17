import React, {Component, useEffect, useState} from "react";
import axios from "axios";
import {Link, withRouter} from "react-router-dom";
import Button from "@material-ui/core/Button";
import {Avatar} from "@material-ui/core";

export class Bookings extends Component{

    constructor(props) {
        super(props);
        this.state = {
            rows1: []
        };
    }

    componentDidMount() {
        axios.get('http://localhost:8080/bookings')
            .then((response => {this.setState({rows: response.data});}))
            .catch((error) => {console.log(error); this.setState({ message: error.message })});
    }

    componentDidUpdate(prevProps, prevState, snapshot) {
        this.componentDidMount();
    }

    render() {
        return (
            <main role="main" className="container">
                <div align="center">
                    {this.state.rows === null && <p>Loading menu...</p>}
                    <br/>
                    <table className="bookings">
                        <thead>
                            <tr>
                                <th width={50}>id</th>
                                <th width={100}>client name</th>
                                <th width={500}>car number</th>
                                <th width={200}>dateStart</th>
                                <th width={50}>dateEnd</th>
                            </tr>
                        </thead>
                        {this.state.rows && this.state.rows.map(booking => (
                            <tbody align="center">
                                <tr>
                                    <td width={50}>{booking.id}</td>
                                    <td width={100}>{booking.client.name}</td>
                                    <td width={100}>{booking.car.model}</td>
                                    <td width={100}>{booking.dateStart}</td>
                                    <td width={100}>{booking.dateEnd}</td>
                                    <Button component={Link} to={'/booking/delete/' + booking.id} variant="contained" color="primary">Delete</Button>
                                    <Button component={Link} to={'/booking/update/' + booking.id} variant="contained" color="primary">Update</Button>
                                </tr>
                            </tbody>
                        ))}
                    </table>
                    <div>
                        <Button component={Link} to="/booking/add" variant="contained" color="primary">Add Booking</Button>
                    </div>
                </div>
            </main>

        );
    }
}
export default withRouter(Bookings);

