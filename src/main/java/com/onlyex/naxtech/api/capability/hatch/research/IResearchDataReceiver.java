package com.onlyex.naxtech.api.capability.hatch.research;


import com.onlyex.naxtech.api.capability.hatch.research.cosmic.ICOResearchDataProvider;
import com.onlyex.naxtech.api.capability.hatch.research.gooware.IGOResearchDataProvider;
import com.onlyex.naxtech.api.capability.hatch.research.optical.IOPResearchDataProvider;
import com.onlyex.naxtech.api.capability.hatch.research.rwu.IResearchDataProvider;
import com.onlyex.naxtech.api.capability.hatch.research.spintronic.ISPResearchDataProvider;
import com.onlyex.naxtech.api.capability.hatch.research.supracausal.ISCAResearchDataProvider;
import com.onlyex.naxtech.api.capability.hatch.research.suprachronal.ISCHResearchDataProvider;
import com.onlyex.naxtech.api.capability.hatch.research.supradimension.ISDIResearchDataProvider;

/**
 * 这个方法用于获取计算提供者（Data Provider）。
 * 返回一个 IResearchDataProvider 接口类型的对象，用于提供计算相关的功能。
 */
public interface IResearchDataReceiver {

    IResearchDataProvider getResearchDataProvider();
    IGOResearchDataProvider getGOResearchDataProvider();
    IOPResearchDataProvider getOPResearchDataProvider();
    ISPResearchDataProvider getSPResearchDataProvider();
    ICOResearchDataProvider getCOResearchDataProvider();
    ISCAResearchDataProvider getSCAResearchDataProvider();
    ISCHResearchDataProvider getSCHResearchDataProvider();
    ISDIResearchDataProvider getSDIResearchDataProvider();


}
