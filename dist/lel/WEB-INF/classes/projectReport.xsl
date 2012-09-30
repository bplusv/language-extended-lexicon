<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:fo="http://www.w3.org/1999/XSL/Format">
    <xsl:output method="xml" indent="yes"/>
    <xsl:template match="/">
        <fo:root>
            <fo:layout-master-set>
                <fo:simple-page-master master-name="A4-portrait"
                                       page-height="29.7cm" page-width="21.0cm" margin="2cm">
                    <fo:region-body margin="1cm" />
                    <fo:region-before extent="1cm" />
                    <fo:region-after extent="1cm" />
                </fo:simple-page-master>
            </fo:layout-master-set>
            <fo:page-sequence master-reference="A4-portrait">
                <fo:static-content flow-name="xsl-region-before">
                    <fo:block text-align="center">
                        Project - 
                        <xsl:value-of select="root/definitions/test" />
                    </fo:block>
                </fo:static-content>
                <fo:static-content flow-name="xsl-region-after">
                    <fo:block text-align="center">
                        Page 
                        <fo:page-number /> of 
                        <fo:page-number-citation ref-id="last-page" /> 
                    </fo:block>
                </fo:static-content>
                <fo:flow flow-name="xsl-region-body">
                    <xsl:for-each select="root/definitions/definition">
                        <fo:block space-after="0.5cm">
                            <fo:inline font-weight="bold">Classification: </fo:inline>
                            <xsl:value-of select="classification" />
                        </fo:block>
                        <fo:block space-after="0.5cm">
                            <fo:inline font-weight="bold">Category: </fo:inline>
                            <xsl:value-of select="category" />
                        </fo:block>
                        <fo:block space-after="0.5cm">
                            <fo:inline font-weight="bold">Symbols: </fo:inline>
                            <xsl:for-each select="symbols/symbol">
                                <fo:inline>
                                    <xsl:value-of select="name" />, 
                                </fo:inline>
                            </xsl:for-each>
                        </fo:block>
                        <fo:block space-after="0.5cm">
                            <fo:inline font-weight="bold">Notion: </fo:inline>
                            <xsl:value-of select="notion" />
                        </fo:block>
                        <fo:block space-after="0.5cm">
                            <fo:inline font-weight="bold">Actual intention: </fo:inline>
                            <xsl:value-of select="actualIntention" />
                        </fo:block>
                        <fo:block space-after="2cm">
                            <fo:inline font-weight="bold">Future intention: </fo:inline>
                            <xsl:value-of select="futureIntention" />
                        </fo:block>
                    </xsl:for-each>
                    <fo:block id="last-page">
                    </fo:block>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
</xsl:stylesheet>