import classNames from "classnames/bind";
import styles from "./DustLevel.module.scss";

import {GiDustCloud} from "react-icons/gi";

const cx = classNames.bind(styles);

function DustLevel({gas}) {
    const dustLevel = gas?.value.toFixed(1)
    return (
        <div className={gas?.gasStatus === '1' ? cx('containerRed'): cx('container')}>
            <div className={cx('description')}>
                <p className={cx('title')}>Khí gas</p>
            </div>
            <div className={cx('img-des')}>
                <p className={cx('dustlevel')}>{dustLevel}</p>
                <GiDustCloud className={cx('icon')}/>
            </div>
        </div>
    );
}


export default DustLevel;