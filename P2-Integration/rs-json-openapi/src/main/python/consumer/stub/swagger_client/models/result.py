# coding: utf-8

"""
    OpenAPI definition

    No description provided (generated by Swagger Codegen https://github.com/swagger-api/swagger-codegen)  # noqa: E501

    OpenAPI spec version: v0
    
    Generated by: https://github.com/swagger-api/swagger-codegen.git
"""

import pprint
import re  # noqa: F401

import six

class Result(object):
    """NOTE: This class is auto generated by the swagger code generator program.

    Do not edit the class manually.
    """
    """
    Attributes:
      swagger_types (dict): The key is attribute name
                            and the value is attribute type.
      attribute_map (dict): The key is attribute name
                            and the value is json key in definition.
    """
    swagger_types = {
        'sum': 'float',
        'difference': 'float',
        'product': 'float',
        'ratio': 'float'
    }

    attribute_map = {
        'sum': 'sum',
        'difference': 'difference',
        'product': 'product',
        'ratio': 'ratio'
    }

    def __init__(self, sum=None, difference=None, product=None, ratio=None):  # noqa: E501
        """Result - a model defined in Swagger"""  # noqa: E501
        self._sum = None
        self._difference = None
        self._product = None
        self._ratio = None
        self.discriminator = None
        if sum is not None:
            self.sum = sum
        if difference is not None:
            self.difference = difference
        if product is not None:
            self.product = product
        if ratio is not None:
            self.ratio = ratio

    @property
    def sum(self):
        """Gets the sum of this Result.  # noqa: E501


        :return: The sum of this Result.  # noqa: E501
        :rtype: float
        """
        return self._sum

    @sum.setter
    def sum(self, sum):
        """Sets the sum of this Result.


        :param sum: The sum of this Result.  # noqa: E501
        :type: float
        """

        self._sum = sum

    @property
    def difference(self):
        """Gets the difference of this Result.  # noqa: E501


        :return: The difference of this Result.  # noqa: E501
        :rtype: float
        """
        return self._difference

    @difference.setter
    def difference(self, difference):
        """Sets the difference of this Result.


        :param difference: The difference of this Result.  # noqa: E501
        :type: float
        """

        self._difference = difference

    @property
    def product(self):
        """Gets the product of this Result.  # noqa: E501


        :return: The product of this Result.  # noqa: E501
        :rtype: float
        """
        return self._product

    @product.setter
    def product(self, product):
        """Sets the product of this Result.


        :param product: The product of this Result.  # noqa: E501
        :type: float
        """

        self._product = product

    @property
    def ratio(self):
        """Gets the ratio of this Result.  # noqa: E501


        :return: The ratio of this Result.  # noqa: E501
        :rtype: float
        """
        return self._ratio

    @ratio.setter
    def ratio(self, ratio):
        """Sets the ratio of this Result.


        :param ratio: The ratio of this Result.  # noqa: E501
        :type: float
        """

        self._ratio = ratio

    def to_dict(self):
        """Returns the model properties as a dict"""
        result = {}

        for attr, _ in six.iteritems(self.swagger_types):
            value = getattr(self, attr)
            if isinstance(value, list):
                result[attr] = list(map(
                    lambda x: x.to_dict() if hasattr(x, "to_dict") else x,
                    value
                ))
            elif hasattr(value, "to_dict"):
                result[attr] = value.to_dict()
            elif isinstance(value, dict):
                result[attr] = dict(map(
                    lambda item: (item[0], item[1].to_dict())
                    if hasattr(item[1], "to_dict") else item,
                    value.items()
                ))
            else:
                result[attr] = value
        if issubclass(Result, dict):
            for key, value in self.items():
                result[key] = value

        return result

    def to_str(self):
        """Returns the string representation of the model"""
        return pprint.pformat(self.to_dict())

    def __repr__(self):
        """For `print` and `pprint`"""
        return self.to_str()

    def __eq__(self, other):
        """Returns true if both objects are equal"""
        if not isinstance(other, Result):
            return False

        return self.__dict__ == other.__dict__

    def __ne__(self, other):
        """Returns true if both objects are not equal"""
        return not self == other
