import classNames from "classnames/bind";
import styles from "./SelectBox.module.scss";

import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import { CiSearch } from "react-icons/ci";
import { useState, useEffect } from "react";

const cx = classNames.bind(styles);

function SelectBox({
    startDate,
    endDate,
    type,
    keyword,
    setStartDate,
    setEndDate,
    setType,
    setKeyword,
}) {
    const [startDate_, setStartDate_] = useState(new Date());
    const [endDate_, setEndDate_] = useState(new Date());
    const [type_, setType_] = useState('dht');
    const [keyword_, setKeyword_] = useState('');
    const handleSearch = () => {
        console.log("Search clicked");
        setStartDate(startDate_)
        setEndDate(endDate_)
        setType(type_)
        setKeyword(keyword_)
    };

    const handleTypeChange = (e) => {
        console.log("Search clicked");
        setType_(e.target.value);
    };

    return (
        <div className={cx("container_select-box")}>
            <div className={cx("item-1")}>
                <span>Year:</span>
                <h3>2023</h3>
            </div>
            <div className={cx("item-2")}>
                <select
                    className={cx("item-2")}
                    value={type_}
                    onChange={handleTypeChange}
                    name="select-type"
                    id="type"
                >
                    <option value="door">Door</option>
                    <option value="dht">Dht</option>
                    <option value="gas">Gas</option>
                </select>
                <div className={cx("item-2")}>
                    <DatePicker
                        selectsStart
                        selected={startDate_}
                        onChange={(date) => setStartDate_(date)}
                        startDate={startDate_}
                    />
                </div>
                <div className={cx("item-2")}>
                    <DatePicker
                        selectsEnd
                        selected={endDate_}
                        onChange={(date) => setEndDate_(date)}
                        endDate={endDate_}
                        startDate={startDate_}
                        minDate={startDate_}
                    />
                </div>

                <div className={cx("item-2")}>
                    <input
                        type="text"
                        onChange={(e) => setKeyword_(e.target.value)}
                        value={keyword_}
                    />
                </div>

                <div className={cx("filter-all")} onClick={handleSearch}>
                    <CiSearch className={cx("icon")} />
                </div>
            </div>
        </div>
    );
}

export default SelectBox;
