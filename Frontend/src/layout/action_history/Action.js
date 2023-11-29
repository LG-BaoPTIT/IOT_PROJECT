import classNames from "classnames/bind";
import styles from "./Styles.module.scss";
import { useState, useEffect } from "react";
import axios from "axios";
import { useLocation } from "react-router-dom";
import Tippy from "@tippyjs/react/headless";
import { HiBars3 } from "react-icons/hi2";
import { IoMdArrowDropdown } from "react-icons/io";

import Nav from "../../components/navbar/Nav";
import SelectBox from "../../components/selectbox/SelectBox";

const cx = classNames.bind(styles);

function History() {
    const location = useLocation();
    const props = location.state;
    const [data, setData] = useState([]);
    const [check, setCheck] = useState(false);
    const [startDate, setStartDate] = useState(new Date());
    const [endDate, setEndDate] = useState(new Date());
    const [type, setType] = useState('dht');
    const [keyword, setKeyword] = useState('');

    const propsData = {
        startDate,
        endDate,
        type,
        keyword,
        setStartDate,
        setEndDate,
        setType,
        setKeyword,
    };

    useEffect(() => {
        axios.post('http://localhost:8080/SearchLog', {
            type,
            start: startDate,
            end: endDate,
            keyWord: keyword
        }).then((res) => setData(res))
    }, [type, startDate, endDate, keyword]);

    const renderTippy = (prop) => {
        return (
            <div>
                <Nav props={props} />
            </div>
        );
    };

    return (
        <div className={cx("ctn")}>
            <div className={cx("container")}>
                <div className={cx("content")}>
                    <div className={cx("card")}>
                        <div className={cx("card-header")}>
                            <h4>Action History</h4>
                            <Tippy
                                render={renderTippy}
                                interactive
                                delay={[200, 100]}
                                offset={[-85, 10]}
                                placement="bottom"
                            >
                                <span className={cx("icon-nav")}>
                                    <HiBars3 className={cx("icon")} />
                                </span>
                            </Tippy>
                        </div>

                        <div className={cx("card-body")}>
                            <SelectBox {...propsData} />

                            {data.length > 0 ? (
                               <table className={cx("table")}>
                               <thead>
                                   <tr>
                                       <th>Type</th>
                                       <th>Home</th>
                                       <th>Date</th>
                                       {/* Add more table headers based on your data structure */}
                                   </tr>
                               </thead>
                               <tbody>
                                   {data.map((item, index) => (
                                       <tr key={index}>
                                           <td>{item.type}</td>
                                           <td>{item.homeId}</td>

                                           <td>{item.start}</td>
                                           {/* Add more table data based on your data structure */}
                                       </tr>
                                   ))}
                               </tbody>
                           </table>
                            ) : (
                                <div className={cx("data-not-found")}>
                                    <span>
                                        <img
                                            src="https://frontend.tikicdn.com/_desktop-next/static/img/account/empty-order.png"
                                            alt=""
                                        />
                                    </span>
                                    <span className={cx("text")}>
                                        No data found for this period of time!
                                    </span>
                                </div>
                            )}
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default History;
