if ${ALL_MODULES}
then
    mvn clean package -PDocker
    docker-compose -f docker-compose.yml up -d

else

    MODULOS=""
    IMAGES=""

    #DOMINIOS

    if ${DOMINIO_HEXA}
    then
        if [ "$MODULOS" != "" ];
        then
            MODULOS="${MODULOS},dominio-hexa"
        else
            MODULOS="dominio-hexa"
        fi
        if [ "$IMAGES" != "" ];
        then
            IMAGES="${IMAGES} hexa-dominio-hexa"
        else
            IMAGES=" hexa-dominio-apl"
        fi
    fi

    #NEGOCIOS

    if ${NEGOCIO_HEXA}
    then
        if [ "$MODULOS" != "" ];
        then
            MODULOS="${MODULOS},negocio-hexa"
        else
            MODULOS="negocio-hexa"
        fi
        if [ "$IMAGES" != "" ];
        then
            IMAGES="${IMAGES} hexa-negocio-hexa"
        else
            IMAGES="hexa-negocio-hexa"
        fi
    fi

    #GATEWAY

    if ${GATEWAY}
    then
        if [ "$MODULOS" != "" ];
        then
            MODULOS="${MODULOS},gateway-server"
        else
            MODULOS="gateway-server"
        fi
        if [ "$IMAGES" != "" ];
        then
            IMAGES="${IMAGES} hexa-gateway"
        else
            IMAGES="hexa-gateway"
        fi
    fi

    #DISCOVERY

    if ${DISCOVERY}
    then
        if [ "$MODULOS" != "" ];
        then
            MODULOS="${MODULOS},discovery-server"
        else
            MODULOS="discovery-server"
        fi
        if [ "$IMAGES" != "" ];
        then
            IMAGES="${IMAGES} hexa-discovery-server"
        else
            IMAGES="hexa-discovery-server"
        fi
    fi

    echo "$MODULOS"
    mvn clean package -PDocker -pl ${MODULOS} -am
    docker-compose -f docker-compose.yml up -d ${IMAGES}

fi