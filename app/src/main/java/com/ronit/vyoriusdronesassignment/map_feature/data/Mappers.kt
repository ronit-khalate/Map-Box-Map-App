package com.ronit.vyoriusdronesassignment.map_feature.data

import com.ronit.vyoriusdronesassignment.map_feature.data.point_info.PointerInfoResponse
import com.ronit.vyoriusdronesassignment.map_feature.domain.dto.PointerInfoDto


fun PointerInfoResponse.toPointerInfoDto():PointerInfoDto{


    if(features.isNotEmpty()){
        return  PointerInfoDto(
                fullAddress = features[0].properties?.full_address,
                name=features[0].properties?.name,
                street =  features[0].properties?.context?.street?.name,
                postalCode = features[0].properties?.context?.postcode?.name,
                place = features[0].properties?.context?.place?.name,
                district = features[0].properties?.context?.district?.name,
                region = features[0].properties?.context?.region?.name
        )
    }

    return PointerInfoDto()


}