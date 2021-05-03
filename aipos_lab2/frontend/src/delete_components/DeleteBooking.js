import React, {Component} from "react";
import axios from 'axios';
import {withRouter, Redirect, Link} from 'react-router-dom';
import * as PropTypes from "prop-types";
import Button from "@material-ui/core/Button";


Redirect.propTypes = {to: PropTypes.string};

class DeleteBooking extends Component {

    constructor(props) {
        super(props);
        this.state = {
            status: ''
        };
    }

    componentDidMount() {
        console.log(this.props);
        axios.delete('http://localhost:8080/deleteBooking/' + this.props.match.params.id)
            .then((response) => {
                this.setState({status: response.data.status});
            })
            .catch((error) => {
                console.log(error);
                this.setState({message: error.message})
            });
    }

    render() {
            return (
                <Redirect to={'/bookings'}/>
            )
    }
}

export default withRouter(DeleteBooking);
