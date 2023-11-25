import classNames from "classnames/bind";
import styles from "./Home.module.scss";
import axios from "axios";
import { useState, useEffect} from "react";
import { useLocation } from "react-router-dom";
import React from "react";
import Tippy from "@tippyjs/react/headless";
import { w3cwebsocket as W3CWebSocket } from "websocket";

import Temperature from "../../components/temperature/Temperature";
import Humidity from "../../components/humidity/Humidity";
import Brightness from "../../components/brightness/Brightness";
import DustLevel from "../../components/dustLevel/DustLevel";
import AreaChart from "../../components/chart/DataSensorChart/AreaChart";
import DustChartComponent from "../../components/chart/DustLevelChart/DustChart";
import Nav from "../../components/navbar/Nav";
// import WebSocket from "../../websocket/WebSoket";

import ImgLight from "../../img/idea.png";
import LightOf from "../../img/big-light.png";
import FanOff from "../../img/fan.png";
import FanOn from "../../img/fan (1).png";
import {HiBars3} from "react-icons/hi2";
import {
    StompSessionProvider,
    useSubscription,
  } from "react-stomp-hooks";
const client = new W3CWebSocket('ws://localhost:8000');
const cx = classNames.bind(styles);

export default function DashBoard () {
    return (
        //Initialize Stomp connection, will use SockJS for http(s) and WebSocket for ws(s)
        //The Connection can be used by all child components via the hooks or hocs.
        <StompSessionProvider
          url={"ws://localhost:8080/ws"}
          //All options supported by @stomp/stompjs can be used here
        >
          <Home />
        </StompSessionProvider>
      );
}

function Home() {
    const location = useLocation();
    const props = location.state;
    const [controlLight1, setControlLight1] = useState(props ? props.stateLed : false);
    const [controlLight2, setControlLight2] = useState(props ? props.stateLed : false);
    const [controlLight3, setControlLight3] = useState(props ? props.stateLed : false);
    const [controlFan, setControlFan] = useState(props ? props.stateFan : false);
    const [dataSensor, setDataSensor] = useState(null);
    const [dustLevel, setDustLevel] = useState(null);
    const [isDustAbout80, setIsDustAbout80] = useState(false);
    const [lastMessage, setLastMessage] = useState("No message received yet");
    useSubscription("/topic/DHT11_data/SMH-001/DHT11_S", (message) => setDataSensor(JSON.parse(message.body)));
    console.log(dataSensor)
    const getFormattedTimestamp = () =>{
        const date = new Date();
        const year = date.getFullYear();
        let month = String(date.getMonth() + 1).padStart(2, '0');
        let day = String(date.getDate()).padStart(2, '0');
        let hour = String(date.getHours()).padStart(2, '0');
        let minute = String(date.getMinutes()).padStart(2, '0');
        let second = String(date.getSeconds()).padStart(2, '0');
        return `${year}-${month}-${day} ${hour}:${minute}:${second}`;
    };

    // useEffect(() => {
    //     const generateRandomDustLevel = () => {
    //         const dust = Math.floor(Math.random() * (100 - 15)) + 15;
    //         setDustLevel(dust);

    //         if (dust >= 80) {
    //             setStateFan(true);
    //             setStateLed(true);
    //             setIsDustAbout80(true);
    //             axios.post('http://localhost:8008/mosquitto/warning')
    //                 .then(response => {
    //                     console.log(response.data);
    //                 })
    //                 .catch(err => {
    //                     console.log(err);
    //                 });
    //         }
    //         else {
    //             setStateFan(false);
    //             setStateLed(false);
    //             setIsDustAbout80(false);
    //         }
    //     }

    //     generateRandomDustLevel();
    //     const dustLevelInterval = setInterval(generateRandomDustLevel, 3000);
    //     return () => {
    //         clearInterval(dustLevelInterval);
    //     };
    // }, []);

    useEffect(() => {
        // if (isDustAbout80) {
        //     const intervalId = setInterval(() => {
        //         setStateFan((prev) => !prev);
        //         setStateLed((prev) => !prev);
        //     }, 300);
        //     return () => {
        //         clearInterval(intervalId);
        //     };
        // };
    }, [isDustAbout80]);

    const renderTippy = (prop) => {
        return (
            <div>
                <Nav props = {{stateLed1: controlLight1, stateLead2: controlLight2, stateLed3: controlLight3, stateFan: controlFan}}/>
            </div>
        )
    }
    
    const handleClick = (deviceType, lightNum) => {
        let status
        let light_id
        const home_id = localStorage.getItem('home_id')

        const time = getFormattedTimestamp();
        if (deviceType === 'Light') {
            if(lightNum === 1) {
                light_id = 'PB_001'
                status = controlLight1 ? 'OFF' : 'ON'
                setControlLight1(prev => !prev)
            }
            if(lightNum === 2) {
                light_id = 'PK_001'
                status = controlLight2 ? 'OFF' : 'ON'
                setControlLight2(prev => !prev)
            }
            if(lightNum === 3) {
                light_id = 'PN_001'
                status = controlLight3 ? 'OFF' : 'ON'
                setControlLight3(prev => !prev)
            }

            axios.post('http://localhost:8080/changeLightStatus', {
                "home_id": home_id,
                "light_id": light_id,
                status,
            })
                .then(res => console.log(res))
                .catch(e => console.log(e))
        }
        else {
            status = controlFan ? 'OFF' : 'ON'
            setControlFan(prev => !prev);

            axios.post('http://localhost:8080/changeDoorStatus', {
                home_id,
                door_id: 'MAIN_DOOR',
                status,
            })
                .then(res => console.log(res))
                .catch(e => console.log(e))
        }
    }
    return (
        <div className={cx('container_app')}>
            <div>
                <div className={cx('title')}>
                    <h3>
                        IoT & Ứng dụng
                    </h3>
                    <Tippy render={renderTippy} interactive delay={[100, 100]}
                        offset={[-85, -3]} placement="bottom"
                    >
                        <span className={cx('icon-nav')}>
                            <HiBars3/>
                        </span>
                    </Tippy>
                </div>
            </div>
            <div className={cx('container_app-header')}>
                <div className={cx('row')}>
                    <div className={cx('col-3')}>
                        <Temperature temp = {dataSensor ? dataSensor.temperature : ''}/>
                    </div>
                    <div className={cx('col-3')}>
                        <Humidity humidity = {dataSensor ? dataSensor.humidity : ''}/>
                    </div>
                    <div className={cx('col-3')}>
                        <DustLevel dustLevel = {dustLevel}/>
                    </div>
                </div>
            </div>
            <div className={cx('container_app-body')}>
                <div className='row'>
                    <div className="col-3">
                        <div className={cx('item-light')}>
                            {isDustAbout80 ? (
                                controlLight1 ? (
                                <img src={ImgLight} alt="Light On" className={cx('light-on')} />
                                ) : (
                                <img src={LightOf} alt="Light Off" className={cx('light-off')} />
                                )
                            ) : controlLight1 ? (
                                <div className={cx('item')}>
                                    <img src={ImgLight} alt="Light On" className={cx('light-on')} />
                                    <button onClick={() => handleClick('Light', 1)} className={cx('off')}>
                                        OFF
                                    </button>
                                </div>
                            ) : (
                                <div  className={cx('item')}>
                                    <img src={LightOf} alt="Light Off" className={cx('light-off')} />
                                    <button onClick={() => handleClick('Light', 1)} className={cx('on')}>
                                        ON
                                    </button>
                                </div>
                            )}
                        </div>
                    </div>
                    <div className="col-3">
                        <div className={cx('item-light')}>
                            {isDustAbout80 ? (
                                controlLight2 ? (
                                <img src={ImgLight} alt="Light On" className={cx('light-on')} />
                                ) : (
                                <img src={LightOf} alt="Light Off" className={cx('light-off')} />
                                )
                            ) : controlLight2 ? (
                                <div className={cx('item')}>
                                    <img src={ImgLight} alt="Light On" className={cx('light-on')} />
                                    <button onClick={() => handleClick('Light', 2)} className={cx('off')}>
                                        OFF
                                    </button>
                                </div>
                            ) : (
                                <div  className={cx('item')}>
                                    <img src={LightOf} alt="Light Off" className={cx('light-off')} />
                                    <button onClick={() => handleClick('Light', 2)} className={cx('on')}>
                                        ON
                                    </button>
                                </div>
                            )}
                        </div>
                    </div>
                    <div className="col-3">
                        <div className={cx('item-light')}>
                            {isDustAbout80 ? (
                                controlLight3 ? (
                                <img src={ImgLight} alt="Light On" className={cx('light-on')} />
                                ) : (
                                <img src={LightOf} alt="Light Off" className={cx('light-off')} />
                                )
                            ) : controlLight3 ? (
                                <div className={cx('item')}>
                                    <img src={ImgLight} alt="Light On" className={cx('light-on')} />
                                    <button onClick={() => handleClick('Light', 3)} className={cx('off')}>
                                        OFF
                                    </button>
                                </div>
                            ) : (
                                <div  className={cx('item')}>
                                    <img src={LightOf} alt="Light Off" className={cx('light-off')} />
                                    <button onClick={() => handleClick('Light', 3)} className={cx('on')}>
                                        ON
                                    </button>
                                </div>
                            )}
                        </div>
                    </div>
                    <div className="col-3">
                        <div className={cx('item-fan')} style={{marginTop: 40}}>
                            {isDustAbout80 ? (
                                controlFan ? (
                                    <img src={ImgLight} alt="Fan On" className={cx('fan-on')} />
                                    ) : (
                                    <img src={LightOf} alt="Fan Off" className={cx('fan-off')} />
                                    )
                            ) : controlFan ? (
                                <div className={cx('item')}>
                                    <img src={FanOn} alt="Fan On" className={cx('fan-on')} />
                                    <button onClick={() => handleClick('Fan')} className={cx('off')}>
                                        OFF
                                    </button>
                                </div>
                            ) : (
                                <div className={cx('item')}>
                                    <img src={FanOff} alt="Fan Off" className={cx('fan-off')} />
                                    <button onClick={() => handleClick('Fan')} className={cx('on')}>
                                        ON
                                    </button>
                                </div>
                            )}
                        </div>
                    </div>
                </div>
                <div className={cx('row')}>
                    {/* <div className={cx('col-6')}>
                        <AreaChart data = {dataSensor ? dataSensor : ''}/>
                    </div> */}
                    <div className={cx('col-6')}>
                        <DustChartComponent data = {dustLevel}/>
                    </div>
                </div>
            </div>
        </div>
    )
}
