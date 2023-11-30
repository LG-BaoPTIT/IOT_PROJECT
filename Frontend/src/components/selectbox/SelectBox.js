import classNames from "classnames/bind";
import styles from "./SelectBox.module.scss";

import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import { CiSearch } from "react-icons/ci";

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
    onSearch,
}) {
    return (
        <div className={cx("container_select-box")}>
            <div className={cx("item-1")}>
                <span>Year:</span>
                <h3>2023</h3>
            </div>
            <div className={cx("item-2")}>
                <select
                    className={cx("item-2")}
                    value={type}
                    onChange={e => setType(e.target.value)}
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
                        selected={startDate}
                        onChange={(date) => setStartDate(date)}
                        startDate={startDate}
                    />
                </div>
                <div className={cx("item-2")}>
                    <DatePicker
                        selectsEnd
                        selected={endDate}
                        onChange={(date) => setEndDate(date)}
                        endDate={endDate}
                        startDate={startDate}
                        minDate={startDate}
                    />
                </div>

                <div className={cx("item-2")}>
                    <input
                        type="text"
                        onChange={(e) => setKeyword(e.target.value)}
                        value={keyword}
                    />
                </div>

                <div className={cx("filter-all")} onClick={onSearch}>
                    <CiSearch className={cx("icon")} />
                </div>
            </div>
        </div>
    );
}

export default SelectBox;
